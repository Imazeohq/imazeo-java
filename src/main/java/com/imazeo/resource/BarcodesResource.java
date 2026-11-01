package com.imazeo.resource;

import com.imazeo.Version;
import com.imazeo.http.Transport;
import com.imazeo.model.BarcodeGenerateInput;
import com.imazeo.model.BinaryResult;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/** Barcode generation resource. */
public final class BarcodesResource {
  private final Transport http;
  private final Supplier<String> projectId;

  public BarcodesResource(Transport http, Supplier<String> projectId) {
    this.http = http;
    this.projectId = projectId;
  }

  public BinaryResult generate(BarcodeGenerateInput input) {
    String pid = projectId.get();
    String url = http.path("projects", pid, "tools", "barcode");
    byte[] body;
    try {
      body = http.mapper().writeValueAsBytes(input);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to serialize barcode request", e);
    }
    Map<String, String> headers = new LinkedHashMap<>();
    headers.put("Content-Type", "application/json");
    headers.put("Accept", "*/*");
    return http.requestBinary("POST", url, headers, body, Version.LIGHT_TIMEOUT_MS);
  }
}
