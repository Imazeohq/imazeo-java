package com.imazeo;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imazeo.model.BarcodeGenerateInput;
import org.junit.jupiter.api.Test;

class MediaContractTest {
  @Test
  void serializesCanonicalBarcodeNames() throws Exception {
    var input = new BarcodeGenerateInput("ean8", "0123456").setFormat("svg").setIncludeText(false);
    var body = new ObjectMapper().valueToTree(input);
    assertEquals(4, body.size());
    assertEquals("ean8", body.get("type").asText());
    assertEquals("0123456", body.get("content").asText());
    assertEquals("svg", body.get("format").asText());
    assertFalse(body.get("includeText").asBoolean());
  }
}
