package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ConversionJob {
  @com.fasterxml.jackson.annotation.JsonProperty("id")
  private String id;
  public String getId() { return id; }
  public ConversionJob setId(String value) { this.id = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("projectId")
  private String projectId;
  public String getProjectId() { return projectId; }
  public ConversionJob setProjectId(String value) { this.projectId = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("status")
  private String status;
  public String getStatus() { return status; }
  public ConversionJob setStatus(String value) { this.status = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("engine")
  private String engine;
  public String getEngine() { return engine; }
  public ConversionJob setEngine(String value) { this.engine = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("options")
  private Map<String, Object> options;
  public Map<String, Object> getOptions() { return options; }
  public ConversionJob setOptions(Map<String, Object> value) { this.options = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("credits")
  private Integer credits;
  public Integer getCredits() { return credits; }
  public ConversionJob setCredits(Integer value) { this.credits = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("createdAt")
  private String createdAt;
  public String getCreatedAt() { return createdAt; }
  public ConversionJob setCreatedAt(String value) { this.createdAt = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("expiresAt")
  private String expiresAt;
  public String getExpiresAt() { return expiresAt; }
  public ConversionJob setExpiresAt(String value) { this.expiresAt = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("artifactId")
  private String artifactId;
  public String getArtifactId() { return artifactId; }
  public ConversionJob setArtifactId(String value) { this.artifactId = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("errorCode")
  private String errorCode;
  public String getErrorCode() { return errorCode; }
  public ConversionJob setErrorCode(String value) { this.errorCode = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("result")
  private ConversionResult result;
  public ConversionResult getResult() { return result; }
  public ConversionJob setResult(ConversionResult value) { this.result = value; return this; }
}
