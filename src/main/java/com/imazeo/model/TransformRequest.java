package com.imazeo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/** Request body for {@code POST .../transforms}. */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class TransformRequest {
  private String imageId;
  private String url;
  private String mode;
  private Long maxOutputBytes;
  private TransformSpec transform;

  public String getImageId() {
    return imageId;
  }

  public TransformRequest setImageId(String imageId) {
    this.imageId = imageId;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public TransformRequest setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getMode() {
    return mode;
  }

  /** One of {@code PERSIST}, {@code CACHE}, {@code EPHEMERAL}. */
  public TransformRequest setMode(String mode) {
    this.mode = mode;
    return this;
  }

  public Long getMaxOutputBytes() {
    return maxOutputBytes;
  }

  public TransformRequest setMaxOutputBytes(Long maxOutputBytes) {
    this.maxOutputBytes = maxOutputBytes;
    return this;
  }

  public TransformSpec getTransform() {
    return transform;
  }

  public TransformRequest setTransform(TransformSpec transform) {
    this.transform = transform;
    return this;
  }
}
