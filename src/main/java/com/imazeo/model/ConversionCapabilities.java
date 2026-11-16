package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ConversionCapabilities {
  @com.fasterxml.jackson.annotation.JsonProperty("enabled")
  private Boolean enabled;
  public Boolean getEnabled() { return enabled; }
  public ConversionCapabilities setEnabled(Boolean value) { this.enabled = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("maxUploadBytes")
  private Integer maxUploadBytes;
  public Integer getMaxUploadBytes() { return maxUploadBytes; }
  public ConversionCapabilities setMaxUploadBytes(Integer value) { this.maxUploadBytes = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("maxPixels")
  private Integer maxPixels;
  public Integer getMaxPixels() { return maxPixels; }
  public ConversionCapabilities setMaxPixels(Integer value) { this.maxPixels = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("maxOutputBytes")
  private Integer maxOutputBytes;
  public Integer getMaxOutputBytes() { return maxOutputBytes; }
  public ConversionCapabilities setMaxOutputBytes(Integer value) { this.maxOutputBytes = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("formats")
  private List<String> formats;
  public List<String> getFormats() { return formats; }
  public ConversionCapabilities setFormats(List<String> value) { this.formats = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("maxPage")
  private Integer maxPage;
  public Integer getMaxPage() { return maxPage; }
  public ConversionCapabilities setMaxPage(Integer value) { this.maxPage = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("temporaryMinutes")
  private Integer temporaryMinutes;
  public Integer getTemporaryMinutes() { return temporaryMinutes; }
  public ConversionCapabilities setTemporaryMinutes(Integer value) { this.temporaryMinutes = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("engines")
  private List<ConversionEngineInfo> engines;
  public List<ConversionEngineInfo> getEngines() { return engines; }
  public ConversionCapabilities setEngines(List<ConversionEngineInfo> value) { this.engines = value; return this; }
}
