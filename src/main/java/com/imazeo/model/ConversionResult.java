package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ConversionResult {
  @com.fasterxml.jackson.annotation.JsonProperty("bytes")
  private Integer bytes;
  public Integer getBytes() { return bytes; }
  public ConversionResult setBytes(Integer value) { this.bytes = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("width")
  private Integer width;
  public Integer getWidth() { return width; }
  public ConversionResult setWidth(Integer value) { this.width = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("height")
  private Integer height;
  public Integer getHeight() { return height; }
  public ConversionResult setHeight(Integer value) { this.height = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("pageCount")
  private Integer pageCount;
  public Integer getPageCount() { return pageCount; }
  public ConversionResult setPageCount(Integer value) { this.pageCount = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("contentType")
  private String contentType;
  public String getContentType() { return contentType; }
  public ConversionResult setContentType(String value) { this.contentType = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("warnings")
  private List<String> warnings;
  public List<String> getWarnings() { return warnings; }
  public ConversionResult setWarnings(List<String> value) { this.warnings = value; return this; }
}
