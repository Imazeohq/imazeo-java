package com.imazeo.http;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imazeo.Options;
import com.imazeo.Version;
import com.imazeo.error.ErrorMapper;
import com.imazeo.error.ImazeoError;
import com.imazeo.error.NetworkError;
import com.imazeo.model.BinaryResult;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Low-level HTTP transport using {@link HttpClient}.
 *
 * <p>Handles JSON envelopes, binary responses, multipart uploads, and 304 not-modified.
 */
public final class Transport {
  static {
    // Ensure User-Agent is accepted on JDKs that treat it as restricted.
    String key = "jdk.httpclient.allowRestrictedHeaders";
    String current = System.getProperty(key);
    if (current == null || current.isBlank()) {
      System.setProperty(key, "user-agent");
    } else if (!current.toLowerCase().contains("user-agent")) {
      System.setProperty(key, current + ",user-agent");
    }
  }

  private final Config config;
  private final HttpClient httpClient;
  private final ObjectMapper mapper;

  public Transport(Config config, HttpClient httpClient, ObjectMapper mapper) {
    this.config = Objects.requireNonNull(config, "config");
    this.httpClient = Objects.requireNonNull(httpClient, "httpClient");
    this.mapper = Objects.requireNonNull(mapper, "mapper");
  }

  public static Transport fromOptions(Options options) {
    Config config = Config.fromOptions(options);
    HttpClient client =
        options.httpClient() != null ? options.httpClient() : Options.defaultHttpClient();
    return new Transport(config, client, createMapper());
  }

  public static ObjectMapper createMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
    mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper;
  }

  public Config config() {
    return config;
  }

  public ObjectMapper mapper() {
    return mapper;
  }

  public String path(String... segments) {
    String joined =
        java.util.Arrays.stream(segments)
            .map(
                s ->
                    URLEncoder.encode(s, StandardCharsets.UTF_8)
                        .replace("+", "%20")
                        .replace("%2F", "/"))
            .collect(Collectors.joining("/"));
    return config.baseUrl() + "/" + config.apiVersion() + "/" + joined;
  }

  public <T> JsonSuccess<T> requestJson(
      String method, String url, Object body, Class<T> type, Long timeoutMs) {
    Map<String, String> headers = new LinkedHashMap<>();
    headers.put("Accept", "application/json");
    byte[] payload = null;
    if (body != null) {
      headers.put("Content-Type", "application/json");
      try {
        payload = mapper.writeValueAsBytes(body);
      } catch (IOException e) {
        throw new IllegalArgumentException("Failed to serialize request body", e);
      }
    }
    HttpResponse<byte[]> response = send(method, url, headers, payload, timeoutMs);
    return parseJsonEnvelope(response, type);
  }

  public <T> JsonSuccess<T> requestJson(
      String method, String url, Object body, com.fasterxml.jackson.core.type.TypeReference<T> type,
      Long timeoutMs) {
    Map<String, String> headers = new LinkedHashMap<>();
    headers.put("Accept", "application/json");
    byte[] payload = null;
    if (body != null) {
      headers.put("Content-Type", "application/json");
      try {
        payload = mapper.writeValueAsBytes(body);
      } catch (IOException e) {
        throw new IllegalArgumentException("Failed to serialize request body", e);
      }
    }
    HttpResponse<byte[]> response = send(method, url, headers, payload, timeoutMs);
    return parseJsonEnvelope(response, type);
  }

  public BinaryResult requestBinary(
      String method, String url, Map<String, String> extraHeaders, byte[] body, Long timeoutMs) {
    Map<String, String> headers = new LinkedHashMap<>();
    headers.put("Accept", "*/*");
    if (extraHeaders != null) headers.putAll(extraHeaders);
    HttpResponse<byte[]> response = send(method, url, headers, body, timeoutMs);
    if (response.statusCode() == 304) {
      Map<String, String> headerMap = headerRecord(response);
      String ifNoneMatch = extraHeaders != null ? extraHeaders.get("If-None-Match") : null;
      String etag = firstHeader(response, "etag");
      if (etag == null) etag = ifNoneMatch;
      return BinaryResult.builder()
          .data(new byte[0])
          .contentType(
              firstHeader(response, "content-type") != null
                  ? firstHeader(response, "content-type")
                  : "application/octet-stream")
          .requestId(nullToEmpty(firstHeader(response, "x-request-id")))
          .headers(headerMap)
          .etag(etag)
          .width(numHeader(response, "x-image-width"))
          .height(numHeader(response, "x-image-height"))
          .format(firstHeader(response, "x-image-format"))
          .transformationId(firstHeader(response, "x-transformation-id"))
          .cache(firstHeader(response, "x-cache"))
          .pdfPage(numHeader(response, "x-pdf-page"))
          .notModified(true)
          .build();
    }
    if (response.statusCode() < 200 || response.statusCode() >= 300) {
      throw errorFromResponse(response);
    }
    byte[] buf = response.body() != null ? response.body() : new byte[0];
    return BinaryResult.builder()
        .data(buf)
        .contentType(
            firstHeader(response, "content-type") != null
                ? firstHeader(response, "content-type")
                : "application/octet-stream")
        .requestId(nullToEmpty(firstHeader(response, "x-request-id")))
        .headers(headerRecord(response))
        .etag(firstHeader(response, "etag"))
        .width(numHeader(response, "x-image-width"))
        .height(numHeader(response, "x-image-height"))
        .format(firstHeader(response, "x-image-format"))
        .transformationId(firstHeader(response, "x-transformation-id"))
        .cache(firstHeader(response, "x-cache"))
        .pdfPage(numHeader(response, "x-pdf-page"))
        .build();
  }

  public <T> JsonSuccess<T> requestMultipartJson(
      String method,
      String url,
      MultipartBody form,
      Integer uploadLength,
      Class<T> type,
      Long timeoutMs) {
    Map<String, String> headers = new LinkedHashMap<>();
    headers.put("Accept", "application/json");
    headers.put("Content-Type", form.contentType());
    if (uploadLength != null) {
      headers.put("X-Upload-Length", String.valueOf(uploadLength));
    }
    HttpResponse<byte[]> response = send(method, url, headers, form.body(), timeoutMs);
    return parseJsonEnvelope(response, type);
  }

  /**
   * Multipart that may return either binary content or a JSON success envelope (PDF multi-page
   * render).
   */
  public Object requestMultipartBinary(
      String method, String url, MultipartBody form, Integer uploadLength, Long timeoutMs) {
    Map<String, String> headers = new LinkedHashMap<>();
    headers.put("Accept", "*/*");
    headers.put("Content-Type", form.contentType());
    if (uploadLength != null) {
      headers.put("X-Upload-Length", String.valueOf(uploadLength));
    }
    HttpResponse<byte[]> response = send(method, url, headers, form.body(), timeoutMs);
    if (response.statusCode() < 200 || response.statusCode() >= 300) {
      throw errorFromResponse(response);
    }
    String ct = firstHeader(response, "content-type");
    if (ct != null && ct.toLowerCase().contains("application/json")) {
      return parseJsonEnvelope(response, JsonNode.class);
    }
    byte[] buf = response.body() != null ? response.body() : new byte[0];
    return BinaryResult.builder()
        .data(buf)
        .contentType(ct != null ? ct : "application/octet-stream")
        .requestId(nullToEmpty(firstHeader(response, "x-request-id")))
        .headers(headerRecord(response))
        .etag(firstHeader(response, "etag"))
        .width(numHeader(response, "x-image-width"))
        .height(numHeader(response, "x-image-height"))
        .format(firstHeader(response, "x-image-format"))
        .pdfPage(numHeader(response, "x-pdf-page"))
        .build();
  }

  private HttpResponse<byte[]> send(
      String method, String url, Map<String, String> headers, byte[] body, Long timeoutMs) {
    long timeout = timeoutMs != null ? timeoutMs : config.timeoutMs();
    HttpRequest.Builder builder =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .timeout(Duration.ofMillis(timeout))
            .header("X-API-Key", config.apiKey())
            .header("User-Agent", config.userAgent());
    for (Map.Entry<String, String> e : headers.entrySet()) {
      if ("X-API-Key".equalsIgnoreCase(e.getKey()) || "User-Agent".equalsIgnoreCase(e.getKey())) {
        continue;
      }
      builder.header(e.getKey(), e.getValue());
    }
    if (body != null) {
      builder.method(method, HttpRequest.BodyPublishers.ofByteArray(body));
    } else {
      builder.method(method, HttpRequest.BodyPublishers.noBody());
    }
    try {
      return httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofByteArray());
    } catch (java.net.http.HttpTimeoutException e) {
      throw new NetworkError("Request timed out after " + timeout + "ms", e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new NetworkError("Request interrupted", e);
    } catch (IOException e) {
      throw new NetworkError(
          e.getMessage() != null ? e.getMessage() : "Network request failed", e);
    }
  }

  private <T> JsonSuccess<T> parseJsonEnvelope(HttpResponse<byte[]> response, Class<T> type) {
    String requestId = firstHeader(response, "x-request-id");
    JsonNode payload = readJson(response, requestId);
    if (response.statusCode() < 200 || response.statusCode() >= 300) {
      throw errorFromPayload(response.statusCode(), payload, requestId);
    }
    return unwrapSuccess(payload, requestId, type, null);
  }

  private <T> JsonSuccess<T> parseJsonEnvelope(
      HttpResponse<byte[]> response, com.fasterxml.jackson.core.type.TypeReference<T> type) {
    String requestId = firstHeader(response, "x-request-id");
    JsonNode payload = readJson(response, requestId);
    if (response.statusCode() < 200 || response.statusCode() >= 300) {
      throw errorFromPayload(response.statusCode(), payload, requestId);
    }
    return unwrapSuccess(payload, requestId, null, type);
  }

  private JsonNode readJson(HttpResponse<byte[]> response, String requestId) {
    byte[] body = response.body();
    if (body == null || body.length == 0) {
      if (response.statusCode() < 200 || response.statusCode() >= 300) {
        throw ErrorMapper.mapHttpError(
            response.statusCode(), null, "Request failed", requestId, null);
      }
      throw new NetworkError("Response was not valid JSON", requestId, null);
    }
    try {
      return mapper.readTree(body);
    } catch (IOException e) {
      if (response.statusCode() < 200 || response.statusCode() >= 300) {
        throw ErrorMapper.mapHttpError(
            response.statusCode(), null, "Request failed", requestId, null);
      }
      throw new NetworkError("Response was not valid JSON", requestId, e);
    }
  }

  @SuppressWarnings("unchecked")
  private <T> JsonSuccess<T> unwrapSuccess(
      JsonNode payload,
      String requestId,
      Class<T> type,
      com.fasterxml.jackson.core.type.TypeReference<T> typeRef) {
    if (payload == null
        || !payload.path("success").asBoolean(false)
        || !payload.has("data")) {
      throw ErrorMapper.mapHttpError(
          200, null, "Unexpected success envelope", requestId, null);
    }
    JsonNode metaNode = payload.path("meta");
    String metaRequestId =
        metaNode.path("requestId").asText(requestId != null ? requestId : "");
    JsonSuccess.Meta meta =
        new JsonSuccess.Meta(
            metaRequestId,
            metaNode.hasNonNull("page") ? metaNode.get("page").asInt() : null,
            metaNode.hasNonNull("limit") ? metaNode.get("limit").asInt() : null,
            metaNode.hasNonNull("total") ? metaNode.get("total").asInt() : null,
            metaNode.hasNonNull("pages") ? metaNode.get("pages").asInt() : null);
    try {
      T data;
      if (typeRef != null) {
        data = mapper.convertValue(payload.get("data"), typeRef);
      } else if (type == JsonNode.class) {
        data = (T) payload.get("data");
      } else {
        data = mapper.convertValue(payload.get("data"), type);
      }
      return new JsonSuccess<>(data, meta);
    } catch (IllegalArgumentException e) {
      throw new NetworkError("Failed to decode response data", metaRequestId, e);
    }
  }

  private ImazeoError errorFromResponse(HttpResponse<byte[]> response) {
    String requestId = firstHeader(response, "x-request-id");
    byte[] body = response.body();
    if (body == null || body.length == 0) {
      return ErrorMapper.mapHttpError(
          response.statusCode(), null, "Request failed", requestId, null);
    }
    try {
      JsonNode payload = mapper.readTree(body);
      return errorFromPayload(response.statusCode(), payload, requestId);
    } catch (IOException e) {
      return ErrorMapper.mapHttpError(
          response.statusCode(), null, "Request failed", requestId, null);
    }
  }

  private ImazeoError errorFromPayload(int status, JsonNode payload, String requestId) {
    JsonNode err = payload != null ? payload.get("error") : null;
    String code = err != null && err.hasNonNull("code") ? err.get("code").asText() : null;
    String message =
        err != null && err.hasNonNull("message") ? err.get("message").asText() : "Request failed";
    Object details = null;
    if (err != null && err.has("details")) {
      details = mapper.convertValue(err.get("details"), Object.class);
    }
    String metaRequestId = requestId;
    if (payload != null && payload.has("meta") && payload.get("meta").hasNonNull("requestId")) {
      metaRequestId = payload.get("meta").get("requestId").asText();
    }
    return ErrorMapper.mapHttpError(status, code, message, metaRequestId, details);
  }

  private static Map<String, String> headerRecord(HttpResponse<?> response) {
    Map<String, String> out = new LinkedHashMap<>();
    response
        .headers()
        .map()
        .forEach(
            (key, values) -> {
              if (values != null && !values.isEmpty()) {
                out.put(key.toLowerCase(), values.get(0));
              }
            });
    return out;
  }

  private static String firstHeader(HttpResponse<?> response, String name) {
    return response.headers().firstValue(name).orElse(null);
  }

  private static Integer numHeader(HttpResponse<?> response, String name) {
    String raw = firstHeader(response, name);
    if (raw == null || raw.isBlank()) return null;
    try {
      return Integer.valueOf(raw.trim());
    } catch (NumberFormatException e) {
      return null;
    }
  }

  private static String nullToEmpty(String value) {
    return value != null ? value : "";
  }

  public static String appendQuery(String url, Map<String, String> params) {
    if (params == null || params.isEmpty()) return url;
    String qs =
        params.entrySet().stream()
            .filter(e -> e.getValue() != null && !e.getValue().isEmpty())
            .map(
                e ->
                    URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8)
                        + "="
                        + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
            .collect(Collectors.joining("&"));
    if (qs.isEmpty()) return url;
    return url + (url.contains("?") ? "&" : "?") + qs;
  }

  /** Immutable transport configuration. */
  public static final class Config {
    private final String apiKey;
    private final String baseUrl;
    private final String apiVersion;
    private final long timeoutMs;
    private final String userAgent;

    public Config(
        String apiKey, String baseUrl, String apiVersion, long timeoutMs, String userAgent) {
      this.apiKey = apiKey;
      this.baseUrl = baseUrl;
      this.apiVersion = apiVersion;
      this.timeoutMs = timeoutMs;
      this.userAgent = userAgent;
    }

    public String apiKey() {
      return apiKey;
    }

    public String baseUrl() {
      return baseUrl;
    }

    public String apiVersion() {
      return apiVersion;
    }

    public long timeoutMs() {
      return timeoutMs;
    }

    public String userAgent() {
      return userAgent;
    }

    public static Config fromOptions(Options options) {
      String apiKey = options.apiKey() != null ? options.apiKey().trim() : "";
      if (apiKey.isEmpty()) throw new IllegalArgumentException("apiKey is required");
      if (apiKey.length() > 128) throw new IllegalArgumentException("apiKey is too long");
      String apiVersion =
          options.apiVersion() != null ? options.apiVersion() : Version.DEFAULT_API_VERSION;
      assertApiVersion(apiVersion);
      String baseUrl =
          normalizeBaseUrl(
              options.baseUrl() != null ? options.baseUrl() : Version.DEFAULT_BASE_URL);
      long timeoutMs = options.timeoutMs() > 0 ? options.timeoutMs() : Version.DEFAULT_TIMEOUT_MS;
      if (timeoutMs < 1) throw new IllegalArgumentException("timeoutMs must be a positive number");
      String ua =
          options.appName() != null && !options.appName().isBlank()
              ? "Imazeo-Java/" + Version.SDK_VERSION + " " + options.appName()
              : "Imazeo-Java/" + Version.SDK_VERSION;
      if (ua.length() > 256) ua = ua.substring(0, 256);
      return new Config(apiKey, baseUrl, apiVersion, timeoutMs, ua);
    }
  }

  public static void assertApiVersion(String value) {
    if (!Version.SUPPORTED_API_VERSIONS.contains(value)) {
      throw new IllegalArgumentException(
          "Unsupported apiVersion \""
              + value
              + "\". Supported: "
              + String.join(", ", Version.SUPPORTED_API_VERSIONS));
    }
  }

  public static String normalizeBaseUrl(String raw) {
    URI uri;
    try {
      uri = URI.create(raw);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid baseUrl: " + raw, e);
    }
    String scheme = uri.getScheme();
    if (scheme == null
        || (!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https"))) {
      throw new IllegalArgumentException("baseUrl must use http or https");
    }
    if (uri.getUserInfo() != null) {
      throw new IllegalArgumentException("baseUrl must not include credentials");
    }
    if (uri.getHost() == null || uri.getHost().isBlank()) {
      throw new IllegalArgumentException("Invalid baseUrl: " + raw);
    }
    String path = uri.getPath() != null ? uri.getPath().replaceAll("/+$", "") : "";
    if ("/v1".equals(path)) path = "";
    String origin = scheme.toLowerCase() + "://" + uri.getRawAuthority();
    if (path.isEmpty() || "/".equals(path)) {
      return origin;
    }
    return (origin + path).replaceAll("/+$", "");
  }
}
