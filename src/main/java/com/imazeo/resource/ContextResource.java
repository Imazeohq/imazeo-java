package com.imazeo.resource;

import com.imazeo.Version;
import com.imazeo.http.Transport;
import com.imazeo.model.ApiKeyContext;

/** API key context resource. */
public final class ContextResource {
  private final Transport http;

  public ContextResource(Transport http) {
    this.http = http;
  }

  public ApiKeyContext get() {
    String url = http.path("api-key", "context");
    return http.requestJson("GET", url, null, ApiKeyContext.class, Version.LIGHT_TIMEOUT_MS).data();
  }
}
