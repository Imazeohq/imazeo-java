package com.imazeo.model;
import java.util.LinkedHashMap;
import java.util.Map;
public record ProcessingOptions(String idempotencyKey, String quoteId) {
  public Map<String, String> headers() {
    Map<String, String> headers = new LinkedHashMap<>();
    if (idempotencyKey != null) headers.put("Idempotency-Key", idempotencyKey);
    if (quoteId != null) headers.put("X-Quote-ID", quoteId);
    return headers;
  }
}
