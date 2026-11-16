package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ToolArtifact {
  @com.fasterxml.jackson.annotation.JsonProperty("_id")
  private String id;
  public String getId() { return id; }
  public ToolArtifact setId(String value) { this.id = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("projectId")
  private String projectId;
  public String getProjectId() { return projectId; }
  public ToolArtifact setProjectId(String value) { this.projectId = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("kind")
  private String kind;
  public String getKind() { return kind; }
  public ToolArtifact setKind(String value) { this.kind = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("displayName")
  private String displayName;
  public String getDisplayName() { return displayName; }
  public ToolArtifact setDisplayName(String value) { this.displayName = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("status")
  private String status;
  public String getStatus() { return status; }
  public ToolArtifact setStatus(String value) { this.status = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("bytes")
  private Integer bytes;
  public Integer getBytes() { return bytes; }
  public ToolArtifact setBytes(Integer value) { this.bytes = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("sha256")
  private String sha256;
  public String getSha256() { return sha256; }
  public ToolArtifact setSha256(String value) { this.sha256 = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("contentType")
  private String contentType;
  public String getContentType() { return contentType; }
  public ToolArtifact setContentType(String value) { this.contentType = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("outputType")
  private String outputType;
  public String getOutputType() { return outputType; }
  public ToolArtifact setOutputType(String value) { this.outputType = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("requestSnapshot")
  private Map<String, Object> requestSnapshot;
  public Map<String, Object> getRequestSnapshot() { return requestSnapshot; }
  public ToolArtifact setRequestSnapshot(Map<String, Object> value) { this.requestSnapshot = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("sourceImageId")
  private String sourceImageId;
  public String getSourceImageId() { return sourceImageId; }
  public ToolArtifact setSourceImageId(String value) { this.sourceImageId = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("createdAt")
  private String createdAt;
  public String getCreatedAt() { return createdAt; }
  public ToolArtifact setCreatedAt(String value) { this.createdAt = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("updatedAt")
  private String updatedAt;
  public String getUpdatedAt() { return updatedAt; }
  public ToolArtifact setUpdatedAt(String value) { this.updatedAt = value; return this; }
}
