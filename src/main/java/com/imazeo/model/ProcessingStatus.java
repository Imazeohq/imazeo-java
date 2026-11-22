package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ProcessingStatus {
  @com.fasterxml.jackson.annotation.JsonProperty("id")
  private String id;
  public String getId() { return id; }
  public ProcessingStatus setId(String value) { this.id = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("kind")
  private String kind;
  public String getKind() { return kind; }
  public ProcessingStatus setKind(String value) { this.kind = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("state")
  private String state;
  public String getState() { return state; }
  public ProcessingStatus setState(String value) { this.state = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("resultExpiresAt")
  private String resultExpiresAt;
  public String getResultExpiresAt() { return resultExpiresAt; }
  public ProcessingStatus setResultExpiresAt(String value) { this.resultExpiresAt = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("hasResult")
  private Boolean hasResult;
  public Boolean getHasResult() { return hasResult; }
  public ProcessingStatus setHasResult(Boolean value) { this.hasResult = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("failureCode")
  private String failureCode;
  public String getFailureCode() { return failureCode; }
  public ProcessingStatus setFailureCode(String value) { this.failureCode = value; return this; }
}
