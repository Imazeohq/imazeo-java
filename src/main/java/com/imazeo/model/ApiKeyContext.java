package com.imazeo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** Result of {@code GET /v1/api-key/context}. */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ApiKeyContext {
  private String projectId;
  private String keyId;

  public ApiKeyContext() {}

  public ApiKeyContext(String projectId, String keyId) {
    this.projectId = projectId;
    this.keyId = keyId;
  }

  public String projectId() {
    return projectId;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String keyId() {
    return keyId;
  }

  public String getKeyId() {
    return keyId;
  }

  public void setKeyId(String keyId) {
    this.keyId = keyId;
  }
}
