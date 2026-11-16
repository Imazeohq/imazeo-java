package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class DynamicCreateInput {
  @com.fasterxml.jackson.annotation.JsonProperty("analyticsEnabled")
  private Boolean analyticsEnabled;
  public Boolean getAnalyticsEnabled() { return analyticsEnabled; }
  public DynamicCreateInput setAnalyticsEnabled(Boolean value) { this.analyticsEnabled = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("name")
  private String name;
  public String getName() { return name; }
  public DynamicCreateInput setName(String value) { this.name = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("destination")
  private String destination;
  public String getDestination() { return destination; }
  public DynamicCreateInput setDestination(String value) { this.destination = value; return this; }
}
