package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class WebsiteOptions {
  @com.fasterxml.jackson.annotation.JsonProperty("page")
  private Integer page;
  public Integer getPage() { return page; }
  public WebsiteOptions setPage(Integer value) { this.page = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("scale")
  private Double scale;
  public Double getScale() { return scale; }
  public WebsiteOptions setScale(Double value) { this.scale = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("format")
  private String format;
  public String getFormat() { return format; }
  public WebsiteOptions setFormat(String value) { this.format = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("quality")
  private Integer quality;
  public Integer getQuality() { return quality; }
  public WebsiteOptions setQuality(Integer value) { this.quality = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("engine")
  private String engine;
  public String getEngine() { return engine; }
  public WebsiteOptions setEngine(String value) { this.engine = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("mode")
  private String mode;
  public String getMode() { return mode; }
  public WebsiteOptions setMode(String value) { this.mode = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("width")
  private Integer width;
  public Integer getWidth() { return width; }
  public WebsiteOptions setWidth(Integer value) { this.width = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("height")
  private Integer height;
  public Integer getHeight() { return height; }
  public WebsiteOptions setHeight(Integer value) { this.height = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("selector")
  private String selector;
  public String getSelector() { return selector; }
  public WebsiteOptions setSelector(String value) { this.selector = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("clipX")
  private Integer clipX;
  public Integer getClipX() { return clipX; }
  public WebsiteOptions setClipX(Integer value) { this.clipX = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("clipY")
  private Integer clipY;
  public Integer getClipY() { return clipY; }
  public WebsiteOptions setClipY(Integer value) { this.clipY = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("clipWidth")
  private Integer clipWidth;
  public Integer getClipWidth() { return clipWidth; }
  public WebsiteOptions setClipWidth(Integer value) { this.clipWidth = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("clipHeight")
  private Integer clipHeight;
  public Integer getClipHeight() { return clipHeight; }
  public WebsiteOptions setClipHeight(Integer value) { this.clipHeight = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("javascriptEnabled")
  private Boolean javascriptEnabled;
  public Boolean getJavascriptEnabled() { return javascriptEnabled; }
  public WebsiteOptions setJavascriptEnabled(Boolean value) { this.javascriptEnabled = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("blockTrackers")
  private Boolean blockTrackers;
  public Boolean getBlockTrackers() { return blockTrackers; }
  public WebsiteOptions setBlockTrackers(Boolean value) { this.blockTrackers = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("autoScroll")
  private Boolean autoScroll;
  public Boolean getAutoScroll() { return autoScroll; }
  public WebsiteOptions setAutoScroll(Boolean value) { this.autoScroll = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("freezeAnimations")
  private Boolean freezeAnimations;
  public Boolean getFreezeAnimations() { return freezeAnimations; }
  public WebsiteOptions setFreezeAnimations(Boolean value) { this.freezeAnimations = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("omitBackground")
  private Boolean omitBackground;
  public Boolean getOmitBackground() { return omitBackground; }
  public WebsiteOptions setOmitBackground(Boolean value) { this.omitBackground = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("colorScheme")
  private String colorScheme;
  public String getColorScheme() { return colorScheme; }
  public WebsiteOptions setColorScheme(String value) { this.colorScheme = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("reducedMotion")
  private Boolean reducedMotion;
  public Boolean getReducedMotion() { return reducedMotion; }
  public WebsiteOptions setReducedMotion(Boolean value) { this.reducedMotion = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("waitUntil")
  private String waitUntil;
  public String getWaitUntil() { return waitUntil; }
  public WebsiteOptions setWaitUntil(String value) { this.waitUntil = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("waitMs")
  private Integer waitMs;
  public Integer getWaitMs() { return waitMs; }
  public WebsiteOptions setWaitMs(Integer value) { this.waitMs = value; return this; }
}
