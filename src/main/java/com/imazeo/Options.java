package com.imazeo;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Objects;

/**
 * Client construction options for {@link Imazeo}.
 *
 * <p>Secret API keys are for trusted servers only. Do not embed keys in browsers.
 */
public final class Options {
  private final String apiKey;
  private final String projectId;
  private final String baseUrl;
  private final String apiVersion;
  private final long timeoutMs;
  private final String appName;
  private final HttpClient httpClient;

  private Options(Builder builder) {
    this.apiKey = builder.apiKey;
    this.projectId = builder.projectId;
    this.baseUrl = builder.baseUrl;
    this.apiVersion = builder.apiVersion;
    this.timeoutMs = builder.timeoutMs;
    this.appName = builder.appName;
    this.httpClient = builder.httpClient;
  }

  public String apiKey() {
    return apiKey;
  }

  /** Optional path project id; when null, resolved via {@code GET /v1/api-key/context}. */
  public String projectId() {
    return projectId;
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

  public String appName() {
    return appName;
  }

  /** Optional injected {@link HttpClient} (tests / custom agents). */
  public HttpClient httpClient() {
    return httpClient;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String apiKey;
    private String projectId;
    private String baseUrl = Version.DEFAULT_BASE_URL;
    private String apiVersion = Version.DEFAULT_API_VERSION;
    private long timeoutMs = Version.DEFAULT_TIMEOUT_MS;
    private String appName;
    private HttpClient httpClient;

    public Builder apiKey(String apiKey) {
      this.apiKey = apiKey;
      return this;
    }

    public Builder projectId(String projectId) {
      this.projectId = projectId;
      return this;
    }

    public Builder baseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public Builder apiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
    }

    public Builder timeoutMs(long timeoutMs) {
      this.timeoutMs = timeoutMs;
      return this;
    }

    public Builder appName(String appName) {
      this.appName = appName;
      return this;
    }

    public Builder httpClient(HttpClient httpClient) {
      this.httpClient = httpClient;
      return this;
    }

    public Options build() {
      Objects.requireNonNull(apiKey, "apiKey is required");
      if (apiKey.isBlank()) {
        throw new IllegalArgumentException("apiKey is required");
      }
      if (timeoutMs < 1) {
        throw new IllegalArgumentException("timeoutMs must be a positive number");
      }
      return new Options(this);
    }
  }

  /** Default shared HttpClient when none is injected. */
  public static HttpClient defaultHttpClient() {
    return HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(30))
        .followRedirects(HttpClient.Redirect.NEVER)
        .build();
  }
}
