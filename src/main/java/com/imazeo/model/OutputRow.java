package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class OutputRow {
  @com.fasterxml.jackson.annotation.JsonProperty("id")
  private String id;
  public String getId() { return id; }
  public OutputRow setId(String value) { this.id = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("kind")
  private String kind;
  public String getKind() { return kind; }
  public OutputRow setKind(String value) { this.kind = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("name")
  private String name;
  public String getName() { return name; }
  public OutputRow setName(String value) { this.name = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("type")
  private String type;
  public String getType() { return type; }
  public OutputRow setType(String value) { this.type = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("status")
  private String status;
  public String getStatus() { return status; }
  public OutputRow setStatus(String value) { this.status = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("createdAt")
  private String createdAt;
  public String getCreatedAt() { return createdAt; }
  public OutputRow setCreatedAt(String value) { this.createdAt = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("bytes")
  private Integer bytes;
  public Integer getBytes() { return bytes; }
  public OutputRow setBytes(Integer value) { this.bytes = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("thumbAvailable")
  private Boolean thumbAvailable;
  public Boolean getThumbAvailable() { return thumbAvailable; }
  public OutputRow setThumbAvailable(Boolean value) { this.thumbAvailable = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("sourceImageId")
  private String sourceImageId;
  public String getSourceImageId() { return sourceImageId; }
  public OutputRow setSourceImageId(String value) { this.sourceImageId = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("contentType")
  private String contentType;
  public String getContentType() { return contentType; }
  public OutputRow setContentType(String value) { this.contentType = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("meta")
  private Map<String, Object> meta;
  public Map<String, Object> getMeta() { return meta; }
  public OutputRow setMeta(Map<String, Object> value) { this.meta = value; return this; }
}
