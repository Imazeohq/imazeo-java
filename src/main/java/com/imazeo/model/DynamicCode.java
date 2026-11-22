package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class DynamicCode {
  @com.fasterxml.jackson.annotation.JsonProperty("analyticsEnabled")
  private Boolean analyticsEnabled;
  public Boolean getAnalyticsEnabled() { return analyticsEnabled; }
  public DynamicCode setAnalyticsEnabled(Boolean value) { this.analyticsEnabled = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("qrOptions")
  private QrGenerateInput qrOptions;
  public QrGenerateInput getQrOptions() { return qrOptions; }
  public DynamicCode setQrOptions(QrGenerateInput value) { this.qrOptions = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("id")
  private String id;
  public String getId() { return id; }
  public DynamicCode setId(String value) { this.id = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("projectId")
  private String projectId;
  public String getProjectId() { return projectId; }
  public DynamicCode setProjectId(String value) { this.projectId = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("name")
  private String name;
  public String getName() { return name; }
  public DynamicCode setName(String value) { this.name = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("slug")
  private String slug;
  public String getSlug() { return slug; }
  public DynamicCode setSlug(String value) { this.slug = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("redirectUrl")
  private String redirectUrl;
  public String getRedirectUrl() { return redirectUrl; }
  public DynamicCode setRedirectUrl(String value) { this.redirectUrl = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("destination")
  private String destination;
  public String getDestination() { return destination; }
  public DynamicCode setDestination(String value) { this.destination = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("status")
  private String status;
  public String getStatus() { return status; }
  public DynamicCode setStatus(String value) { this.status = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("suspended")
  private Boolean suspended;
  public Boolean getSuspended() { return suspended; }
  public DynamicCode setSuspended(Boolean value) { this.suspended = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("revision")
  private Integer revision;
  public Integer getRevision() { return revision; }
  public DynamicCode setRevision(Integer value) { this.revision = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("createdAt")
  private String createdAt;
  public String getCreatedAt() { return createdAt; }
  public DynamicCode setCreatedAt(String value) { this.createdAt = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("updatedAt")
  private String updatedAt;
  public String getUpdatedAt() { return updatedAt; }
  public DynamicCode setUpdatedAt(String value) { this.updatedAt = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("recordedScans")
  private Integer recordedScans;
  public Integer getRecordedScans() { return recordedScans; }
  public DynamicCode setRecordedScans(Integer value) { this.recordedScans = value; return this; }
}
