package com.imazeo.resource;

import com.imazeo.Version;
import com.imazeo.http.Transport;
import com.imazeo.model.BinaryResult;
import com.imazeo.model.QrGenerateInput;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/** QR code generation resource. */
public final class QrResource {
  private final Transport http;
  private final Supplier<String> projectId;

  public QrResource(Transport http, Supplier<String> projectId) {
    this.http = http;
    this.projectId = projectId;
  }

  public BinaryResult generate(QrGenerateInput input) {
    String pid = projectId.get();
    String url = http.path("projects", pid, "tools", "qr");
    byte[] body;
    try {
      body = http.mapper().writeValueAsBytes(input);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to serialize QR request", e);
    }
    Map<String, String> headers = new LinkedHashMap<>();
    headers.put("Content-Type", "application/json");
    headers.put("Accept", "*/*");
    return http.requestBinary("POST", url, headers, body, Version.LIGHT_TIMEOUT_MS);
  }
}
