package com.imazeo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Stored image record. */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ImageRecord {
  @JsonProperty("_id")
  private String id;

  private String projectId;
  private String sourceType;
  private String originalName;
  private long bytes;
  private String sha256;
  private ImageMetadata metadata;
  private String status;
  private String createdAt;
  private String updatedAt;

  public ImageRecord() {}

  public String getId() {
    return id;
  }

  /** Convenience alias for {@link #getId()}. */
  public String id() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /** Alias matching TypeScript {@code _id}. */
  public String get_id() {
    return id;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getSourceType() {
    return sourceType;
  }

  public void setSourceType(String sourceType) {
    this.sourceType = sourceType;
  }

  public String getOriginalName() {
    return originalName;
  }

  public void setOriginalName(String originalName) {
    this.originalName = originalName;
  }

  public long getBytes() {
    return bytes;
  }

  public void setBytes(long bytes) {
    this.bytes = bytes;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}
