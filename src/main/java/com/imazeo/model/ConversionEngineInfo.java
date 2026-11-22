package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ConversionEngineInfo {
  @com.fasterxml.jackson.annotation.JsonProperty("id")
  private String id;
  public String getId() { return id; }
  public ConversionEngineInfo setId(String value) { this.id = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("enabled")
  private Boolean enabled;
  public Boolean getEnabled() { return enabled; }
  public ConversionEngineInfo setEnabled(Boolean value) { this.enabled = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("label")
  private String label;
  public String getLabel() { return label; }
  public ConversionEngineInfo setLabel(String value) { this.label = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("unit")
  private String unit;
  public String getUnit() { return unit; }
  public ConversionEngineInfo setUnit(String value) { this.unit = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("maxUnits")
  private Integer maxUnits;
  public Integer getMaxUnits() { return maxUnits; }
  public ConversionEngineInfo setMaxUnits(Integer value) { this.maxUnits = value; return this; }
}
