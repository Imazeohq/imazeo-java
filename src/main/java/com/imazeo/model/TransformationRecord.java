package com.imazeo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Stored transformation record. */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TransformationRecord {
  @JsonProperty("_id")
  private String id;

  private String projectId;
  private String imageId;
  private String sourceHash;
  private String transformHash;
  private TransformSpec spec;
  private String mode;
  private long bytes;
  private long processingMs;
  private String status;
  private String sha256;
  private ImageMetadata metadata;
  private String expiresAt;
  private String createdAt;

  public TransformationRecord() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String get_id() {
    return id;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getImageId() {
    return imageId;
  }

  public void setImageId(String imageId) {
    this.imageId = imageId;
  }

  public String getSourceHash() {
    return sourceHash;
  }

  public void setSourceHash(String sourceHash) {
    this.sourceHash = sourceHash;
  }

  public String getTransformHash() {
    return transformHash;
  }

  public void setTransformHash(String transformHash) {
    this.transformHash = transformHash;
  }

  public TransformSpec getSpec() {
    return spec;
  }

  public void setSpec(TransformSpec spec) {
    this.spec = spec;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public long getBytes() {
    return bytes;
  }

  public void setBytes(long bytes) {
    this.bytes = bytes;
  }

  public long getProcessingMs() {
    return processingMs;
  }

  public void setProcessingMs(long processingMs) {
    this.processingMs = processingMs;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSha256() {
    return sha256;
  }

  public void setSha256(String sha256) {
    this.sha256 = sha256;
  }

  public ImageMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(ImageMetadata metadata) {
    this.metadata = metadata;
  }

  public String getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(String expiresAt) {
    this.expiresAt = expiresAt;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}
