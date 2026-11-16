package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ScanAnalytics {
  @com.fasterxml.jackson.annotation.JsonProperty("days")
  private Integer days;
  public Integer getDays() { return days; }
  public ScanAnalytics setDays(Integer value) { this.days = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("total")
  private Integer total;
  public Integer getTotal() { return total; }
  public ScanAnalytics setTotal(Integer value) { this.total = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("retentionDays")
  private Integer retentionDays;
  public Integer getRetentionDays() { return retentionDays; }
  public ScanAnalytics setRetentionDays(Integer value) { this.retentionDays = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("bestEffort")
  private Boolean bestEffort;
  public Boolean getBestEffort() { return bestEffort; }
  public ScanAnalytics setBestEffort(Boolean value) { this.bestEffort = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("series")
  private List<ScanPoint> series;
  public List<ScanPoint> getSeries() { return series; }
  public ScanAnalytics setSeries(List<ScanPoint> value) { this.series = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("devices")
  private List<ScanBreakdown> devices;
  public List<ScanBreakdown> getDevices() { return devices; }
  public ScanAnalytics setDevices(List<ScanBreakdown> value) { this.devices = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("browsers")
  private List<ScanBreakdown> browsers;
  public List<ScanBreakdown> getBrowsers() { return browsers; }
  public ScanAnalytics setBrowsers(List<ScanBreakdown> value) { this.browsers = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("countries")
  private List<ScanBreakdown> countries;
  public List<ScanBreakdown> getCountries() { return countries; }
  public ScanAnalytics setCountries(List<ScanBreakdown> value) { this.countries = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("recent")
  private List<ScanRecent> recent;
  public List<ScanRecent> getRecent() { return recent; }
  public ScanAnalytics setRecent(List<ScanRecent> value) { this.recent = value; return this; }
}
