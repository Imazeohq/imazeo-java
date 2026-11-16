package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class DynamicEditInput {
  @com.fasterxml.jackson.annotation.JsonProperty("analyticsEnabled")
  private Boolean analyticsEnabled;
  public Boolean getAnalyticsEnabled() { return analyticsEnabled; }
  public DynamicEditInput setAnalyticsEnabled(Boolean value) { this.analyticsEnabled = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("qrOptions")
  private QrGenerateInput qrOptions;
  public QrGenerateInput getQrOptions() { return qrOptions; }
  public DynamicEditInput setQrOptions(QrGenerateInput value) { this.qrOptions = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("expectedRevision")
  private Integer expectedRevision;
  public Integer getExpectedRevision() { return expectedRevision; }
  public DynamicEditInput setExpectedRevision(Integer value) { this.expectedRevision = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("name")
  private String name;
  public String getName() { return name; }
  public DynamicEditInput setName(String value) { this.name = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("destination")
  private String destination;
  public String getDestination() { return destination; }
  public DynamicEditInput setDestination(String value) { this.destination = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("status")
  private String status;
  public String getStatus() { return status; }
  public DynamicEditInput setStatus(String value) { this.status = value; return this; }
}
