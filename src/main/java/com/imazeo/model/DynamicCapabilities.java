package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class DynamicCapabilities {
  @com.fasterxml.jackson.annotation.JsonProperty("enabled")
  private Boolean enabled;
  public Boolean getEnabled() { return enabled; }
  public DynamicCapabilities setEnabled(Boolean value) { this.enabled = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("canEdit")
  private Boolean canEdit;
  public Boolean getCanEdit() { return canEdit; }
  public DynamicCapabilities setCanEdit(Boolean value) { this.canEdit = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("analytics")
  private Boolean analytics;
  public Boolean getAnalytics() { return analytics; }
  public DynamicCapabilities setAnalytics(Boolean value) { this.analytics = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("activeCount")
  private Integer activeCount;
  public Integer getActiveCount() { return activeCount; }
  public DynamicCapabilities setActiveCount(Integer value) { this.activeCount = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("maxActiveCodes")
  private Integer maxActiveCodes;
  public Integer getMaxActiveCodes() { return maxActiveCodes; }
  public DynamicCapabilities setMaxActiveCodes(Integer value) { this.maxActiveCodes = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("retentionDays")
  private Integer retentionDays;
  public Integer getRetentionDays() { return retentionDays; }
  public DynamicCapabilities setRetentionDays(Integer value) { this.retentionDays = value; return this; }
}
