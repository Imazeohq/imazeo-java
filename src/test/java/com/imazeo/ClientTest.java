package com.imazeo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.imazeo.error.AuthenticationError;
import com.imazeo.error.RateLimitError;
import com.imazeo.error.ValidationError;
import com.imazeo.http.Transport;
import com.imazeo.model.BinaryResult;
import com.imazeo.model.ImageRecord;
import com.imazeo.model.Page;
import com.imazeo.model.PageParams;
import com.imazeo.model.QrGenerateInput;
import com.imazeo.model.TransformRequest;
import com.imazeo.model.TransformSpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.Buffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientTest {
  @Test
  void defaultTransportDoesNotForwardProjectKeyAcrossRedirect() throws Exception {
    try (MockWebServer destination = new MockWebServer()) {
      destination.start();
      destination.enqueue(jsonOk("{\"projectId\":\"p\",\"keyId\":\"k\"}"));
      server.enqueue(new MockResponse().setResponseCode(307)
          .addHeader("Location", destination.url("/")));
      assertThrows(Exception.class, () -> client(null).context.get());
      assertEquals("secret-key", server.takeRequest().getHeader("X-API-Key"));
      assertEquals(0, destination.getRequestCount());
    }
  }

  private MockWebServer server;

  @BeforeEach
  void setUp() throws IOException {
    server = new MockWebServer();
    server.start();
  }

  @AfterEach
  void tearDown() throws IOException {
    server.shutdown();
  }

  private String baseUrl() {
    return server.url("/").toString().replaceAll("/$", "");
  }

  private Imazeo client(String projectId) {
    Options.Builder b =
        Options.builder().apiKey("secret-key").baseUrl(baseUrl());
    if (projectId != null) {
      b.projectId(projectId);
    }
    return new Imazeo(b.build());
  }

  private static MockResponse jsonOk(String dataJson) {
    return new MockResponse()
        .setResponseCode(200)
        .addHeader("Content-Type", "application/json")
        .addHeader("x-request-id", "req_test")
        .setBody(
            "{\"success\":true,\"data\":"
                + dataJson
                + ",\"meta\":{\"requestId\":\"req_test\"}}");
  }

  private static MockResponse jsonError(int status, String code, String message) {
    return new MockResponse()
        .setResponseCode(status)
        .addHeader("Content-Type", "application/json")
        .addHeader("x-request-id", "req_test")
        .setBody(
            "{\"success\":false,\"error\":{\"code\":\""
                + code
                + "\",\"message\":\""
                + message
                + "\"},\"meta\":{\"requestId\":\"req_test\"}}");
  }

  @Test
  void normalizesBaseUrlAndStripsV1() {
    assertEquals("https://api.example.com", Transport.normalizeBaseUrl("https://api.example.com/"));
    assertEquals(
        "https://api.example.com", Transport.normalizeBaseUrl("https://api.example.com/v1"));
    assertThrows(IllegalArgumentException.class, () -> Transport.normalizeBaseUrl("ftp://x"));
  }

  @Test
  void rejectsUnsupportedApiVersion() {
    IllegalArgumentException ex =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new Imazeo(
                    Options.builder().apiKey("key").apiVersion("v9").baseUrl(baseUrl()).build()));
    assertTrue(ex.getMessage().contains("Unsupported apiVersion"));
  }

  @Test
  void sendsApiKeyAndUserAgent() throws Exception {
    server.enqueue(
        jsonOk(
            "{\"projectId\":\"proj_1\",\"keyId\":\"key_1\"}"));

    var ctx = client("p").context.get();
    assertEquals("proj_1", ctx.projectId());

    RecordedRequest req = server.takeRequest();
    assertEquals("/v1/api-key/context", req.getPath());
    assertEquals("secret-key", req.getHeader("X-API-Key"));
    assertTrue(req.getHeader("User-Agent").startsWith("Imazeo-Java/1.0.0"));
    assertEquals(null, req.getHeader("Authorization"));
  }

  @Test
  void resolvesProjectIdFromContextWhenOmitted() throws Exception {
    server.enqueue(
        jsonOk(
            "{\"projectId\":\"proj_auto\",\"keyId\":\"k\"}"));
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json")
            .addHeader("x-request-id", "req_test")
            .setBody(
                "{\"success\":true,\"data\":[],\"meta\":{\"requestId\":\"req_test\",\"page\":1,\"limit\":10,\"total\":0,\"pages\":0}}"));

    Imazeo imazeo = client(null);
    Page<ImageRecord> page =
        imazeo.images.list(PageParams.builder().page(1).limit(10).build());
    assertTrue(page.items().isEmpty());
    assertEquals("req_test", page.requestId());

    server.takeRequest(); // context
    RecordedRequest list = server.takeRequest();
    assertTrue(list.getPath().contains("/v1/projects/proj_auto/images"));
  }

  @Test
  void uploadsWithUploadLengthAndMultipartFileField() throws Exception {
    server.enqueue(
        new MockResponse()
            .setResponseCode(201)
            .addHeader("Content-Type", "application/json")
            .addHeader("x-request-id", "req_test")
            .setBody(
                "{\"success\":true,\"data\":{"
                    + "\"_id\":\"img_1\",\"projectId\":\"p1\",\"sourceType\":\"upload\","
                    + "\"originalName\":\"a.png\",\"bytes\":4,\"sha256\":\"abc\","
                    + "\"metadata\":{\"format\":\"png\",\"mimeType\":\"image/png\",\"width\":1,\"height\":1,"
                    + "\"frames\":1,\"pixelCount\":1,\"hasAlpha\":false,\"orientation\":null},"
                    + "\"status\":\"ready\",\"createdAt\":\"2026-01-01T00:00:00.000Z\","
                    + "\"updatedAt\":\"2026-01-01T00:00:00.000Z\""
                    + "},\"meta\":{\"requestId\":\"req_test\"}}"));

    byte[] bytes = new byte[] {1, 2, 3, 4};
    ImageRecord image = client("p1").images.upload(bytes, "a.png", "image/png");
    assertEquals("img_1", image.id());

    RecordedRequest req = server.takeRequest();
    assertEquals("4", req.getHeader("X-Upload-Length"));
    assertTrue(req.getHeader("Content-Type").startsWith("multipart/form-data"));
    Buffer body = req.getBody();
    String bodyText = body.readString(StandardCharsets.UTF_8);
    assertTrue(bodyText.contains("name=\"file\""));
    assertTrue(bodyText.contains("filename=\"a.png\""));
  }

  @Test
  void mapsErrorEnvelopesToTypedErrors() {
    server.enqueue(jsonError(401, "API_KEY_INVALID", "bad key"));
    assertThrows(AuthenticationError.class, () -> client("p").images.get("x"));

    server.enqueue(jsonError(429, "RATE_LIMIT_EXCEEDED", "slow down"));
    assertThrows(RateLimitError.class, () -> client("p").images.get("x"));

    server.enqueue(jsonError(400, "TOOL_INVALID", "bad"));
    assertThrows(
        ValidationError.class,
        () -> client("p").qr.generate(new QrGenerateInput("hi")));
  }

  @Test
  void returnsBinaryResultsForQrGeneration() throws Exception {
    byte[] png = new byte[] {(byte) 137, 80, 78, 71};
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type", "image/png")
            .addHeader("x-request-id", "req_bin")
            .setBody(new Buffer().write(png)));

    BinaryResult result =
        client("p")
            .qr
            .generate(
                new QrGenerateInput("https://imazeo.com").setFormat("png").setSize(256));
    assertEquals("image/png", result.contentType());
    assertArrayEquals(png, result.data());
    assertEquals("req_bin", result.requestId());
  }

  @Test
  void passesImageListQFilter() throws Exception {
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json")
            .addHeader("x-request-id", "req_test")
            .setBody(
                "{\"success\":true,\"data\":[],\"meta\":{\"requestId\":\"req_test\"}}"));

    client("p").images.list(PageParams.builder().q("photo").page(1).build());
    RecordedRequest req = server.takeRequest();
    assertTrue(req.getPath().contains("q=photo"));
  }

  @Test
  void returnsNotModifiedOnTransformDownload304() throws Exception {
    server.enqueue(
        new MockResponse()
            .setResponseCode(304)
            .addHeader("etag", "\"abc\"")
            .addHeader("x-request-id", "req_304"));

    BinaryResult result = client("p").transforms.download("tr_1", "\"abc\"");
    assertTrue(result.notModified());
    assertEquals(0, result.data().length);
    assertEquals("req_304", result.requestId());
    assertEquals("\"abc\"", result.etag());

    RecordedRequest req = server.takeRequest();
    assertEquals("\"abc\"", req.getHeader("If-None-Match"));
  }

  @Test
  void postsTransformJsonAndReturnsBinary() throws Exception {
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type", "image/webp")
            .addHeader("x-transformation-id", "tr_1")
            .addHeader("x-cache", "MISS")
            .addHeader("x-request-id", "req_t")
            .setBody(new Buffer().write(new byte[] {1, 2})));

    BinaryResult out =
        client("p")
            .transforms
            .create(
                new TransformRequest()
                    .setImageId("img")
                    .setTransform(new TransformSpec().setWidth(100).setFormat("webp")));
    assertEquals("tr_1", out.transformationId());
    assertEquals("MISS", out.cache());

    RecordedRequest req = server.takeRequest();
    assertEquals("POST", req.getMethod());
    String body = req.getBody().readString(StandardCharsets.UTF_8);
    assertTrue(body.contains("\"imageId\":\"img\""));
    assertTrue(body.contains("\"width\":100"));
    assertTrue(body.contains("\"format\":\"webp\""));
  }

  @Test
  void reportsSdkVersionAndApiCompatibility() {
    assertEquals("1.0.0", Imazeo.version());
    assertEquals("v1", Imazeo.apiCompatibility().defaultApi());
    assertTrue(Imazeo.apiCompatibility().supported().contains("v1"));
  }
}
