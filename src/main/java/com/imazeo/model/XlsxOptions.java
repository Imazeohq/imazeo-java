package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class XlsxOptions {
  @com.fasterxml.jackson.annotation.JsonProperty("page")
  private Integer page;
  public Integer getPage() { return page; }
  public XlsxOptions setPage(Integer value) { this.page = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("scale")
  private Double scale;
  public Double getScale() { return scale; }
  public XlsxOptions setScale(Double value) { this.scale = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("format")
  private String format;
  public String getFormat() { return format; }
  public XlsxOptions setFormat(String value) { this.format = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("quality")
  private Integer quality;
  public Integer getQuality() { return quality; }
  public XlsxOptions setQuality(Integer value) { this.quality = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("engine")
  private String engine;
  public String getEngine() { return engine; }
  public XlsxOptions setEngine(String value) { this.engine = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("sheet")
  private Integer sheet;
  public Integer getSheet() { return sheet; }
  public XlsxOptions setSheet(Integer value) { this.sheet = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("renderMode")
  private String renderMode;
  public String getRenderMode() { return renderMode; }
  public XlsxOptions setRenderMode(String value) { this.renderMode = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("range")
  private String range;
  public String getRange() { return range; }
  public XlsxOptions setRange(String value) { this.range = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("showGridLines")
  private Boolean showGridLines;
  public Boolean getShowGridLines() { return showGridLines; }
  public XlsxOptions setShowGridLines(Boolean value) { this.showGridLines = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("paperSize")
  private String paperSize;
  public String getPaperSize() { return paperSize; }
  public XlsxOptions setPaperSize(String value) { this.paperSize = value; return this; }
}
