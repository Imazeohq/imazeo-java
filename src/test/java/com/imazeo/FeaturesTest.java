package com.imazeo;

import static org.junit.jupiter.api.Assertions.*;
import com.imazeo.model.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

class FeaturesTest {
  private Imazeo client(MockWebServer server) {
    return new Imazeo(Options.builder().apiKey("test-key").projectId("project")
        .baseUrl(server.url("/").toString()).build());
  }
  private MockResponse success(String data) {
    return new MockResponse().addHeader("Content-Type", "application/json")
        .setBody("{\"success\":true,\"data\":" + data + ",\"meta\":{\"requestId\":\"request\"}}");
  }
  @Test void conversionHeadersAndOptions() throws Exception {
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(success("{\"id\":\"job\",\"status\":\"queued\"}"));
      var job = client(server).conversions.xlsx(new UploadSource(new byte[]{1,2,3}),
          new ProcessingOptions("operation-key", "quote-id"), new XlsxOptions().setShowGridLines(false));
      var request = server.takeRequest(1, TimeUnit.SECONDS);
      assertNotNull(request);
      assertEquals("/v1/projects/project/conversions/xlsx?showGridLines=false", request.getPath());
      assertEquals("operation-key", request.getHeader("Idempotency-Key"));
      assertEquals("quote-id", request.getHeader("X-Quote-ID"));
      assertEquals("3", request.getHeader("X-Upload-Length"));
      assertEquals("test-key", request.getHeader("X-API-Key"));
      assertNull(request.getHeader("Authorization"));
      assertEquals("queued", job.getStatus());
    }
  }
  @Test void dynamicRevisionAndFalseAnalytics() throws Exception {
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(success("{\"revision\":1}"));
      client(server).dynamicCodes.update("code", new DynamicEditInput().setExpectedRevision(0).setAnalyticsEnabled(false), "edit-key");
      var request = server.takeRequest(1, TimeUnit.SECONDS);
      assertNotNull(request);
      assertEquals("PATCH", request.getMethod());
      assertEquals("edit-key", request.getHeader("Idempotency-Key"));
      var body = com.imazeo.http.Transport.createMapper().readTree(request.getBody().readUtf8());
      assertEquals(0, body.get("expectedRevision").asInt());
      assertFalse(body.get("analyticsEnabled").asBoolean());
    }
  }
  @Test void artifactTextFieldsAndSnapshot() throws Exception {
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(success("{\"_id\":\"saved\"}"));
      client(server).artifacts.save(new UploadSource(new byte[]{1}), new ArtifactSaveInput("qr", "Company QR", "image/png", Map.of("format", "png"), null));
      var request = server.takeRequest(1, TimeUnit.SECONDS);
      assertNotNull(request);
      String body = request.getBody().readUtf8();
      assertTrue(body.contains("name=\"kind\"\r\n"));
      assertFalse(body.contains("name=\"kind\"; filename="));
      assertTrue(body.contains("{\"format\":\"png\"}"));
    }
  }
  @Test void structuredOperationResultAndScanFilters() throws Exception {
    try (MockWebServer server = new MockWebServer()) {
      server.enqueue(success("{\"pages\":[{\"pageNumber\":1}]}"));
      var sdk = client(server);
      var data = (com.fasterxml.jackson.databind.JsonNode) sdk.operations.result("operation");
      assertEquals(1, data.path("pages").get(0).path("pageNumber").asInt());
      assertNotNull(server.takeRequest(1, TimeUnit.SECONDS));
      server.enqueue(new MockResponse().addHeader("Content-Type", "text/csv").setBody("date,count\n"));
      var csv = sdk.dynamicCodes.exportAnalytics("code", Map.of("bot", "false", "browser", "chrome"));
      var request = server.takeRequest(1, TimeUnit.SECONDS);
      assertNotNull(request);
      assertTrue(request.getPath().contains("bot=false"));
      assertEquals("text/csv", csv.contentType());
    }
  }
}
