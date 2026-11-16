package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class PptxOptions {
  @com.fasterxml.jackson.annotation.JsonProperty("page")
  private Integer page;
  public Integer getPage() { return page; }
  public PptxOptions setPage(Integer value) { this.page = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("scale")
  private Double scale;
  public Double getScale() { return scale; }
  public PptxOptions setScale(Double value) { this.scale = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("format")
  private String format;
  public String getFormat() { return format; }
  public PptxOptions setFormat(String value) { this.format = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("quality")
  private Integer quality;
  public Integer getQuality() { return quality; }
  public PptxOptions setQuality(Integer value) { this.quality = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("engine")
  private String engine;
  public String getEngine() { return engine; }
  public PptxOptions setEngine(String value) { this.engine = value; return this; }
}
