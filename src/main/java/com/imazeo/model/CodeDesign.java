package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CodeDesign {
  @com.fasterxml.jackson.annotation.JsonProperty("moduleShape")
  private String moduleShape;
  public String getModuleShape() { return moduleShape; }
  public CodeDesign setModuleShape(String value) { this.moduleShape = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("finderOuter")
  private String finderOuter;
  public String getFinderOuter() { return finderOuter; }
  public CodeDesign setFinderOuter(String value) { this.finderOuter = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("finderInner")
  private String finderInner;
  public String getFinderInner() { return finderInner; }
  public CodeDesign setFinderInner(String value) { this.finderInner = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("fill")
  private String fill;
  public String getFill() { return fill; }
  public CodeDesign setFill(String value) { this.fill = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("textAlign")
  private String textAlign;
  public String getTextAlign() { return textAlign; }
  public CodeDesign setTextAlign(String value) { this.textAlign = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("fontFamily")
  private String fontFamily;
  public String getFontFamily() { return fontFamily; }
  public CodeDesign setFontFamily(String value) { this.fontFamily = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("fontWeight")
  private String fontWeight;
  public String getFontWeight() { return fontWeight; }
  public CodeDesign setFontWeight(String value) { this.fontWeight = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("barTextAlign")
  private String barTextAlign;
  public String getBarTextAlign() { return barTextAlign; }
  public CodeDesign setBarTextAlign(String value) { this.barTextAlign = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("barTextPosition")
  private String barTextPosition;
  public String getBarTextPosition() { return barTextPosition; }
  public CodeDesign setBarTextPosition(String value) { this.barTextPosition = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("moduleScale")
  private Integer moduleScale;
  public Integer getModuleScale() { return moduleScale; }
  public CodeDesign setModuleScale(Integer value) { this.moduleScale = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("gradientAngle")
  private Integer gradientAngle;
  public Integer getGradientAngle() { return gradientAngle; }
  public CodeDesign setGradientAngle(Integer value) { this.gradientAngle = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("logoScale")
  private Integer logoScale;
  public Integer getLogoScale() { return logoScale; }
  public CodeDesign setLogoScale(Integer value) { this.logoScale = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("logoPadding")
  private Integer logoPadding;
  public Integer getLogoPadding() { return logoPadding; }
  public CodeDesign setLogoPadding(Integer value) { this.logoPadding = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("captionSize")
  private Integer captionSize;
  public Integer getCaptionSize() { return captionSize; }
  public CodeDesign setCaptionSize(Integer value) { this.captionSize = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("quietZone")
  private Integer quietZone;
  public Integer getQuietZone() { return quietZone; }
  public CodeDesign setQuietZone(Integer value) { this.quietZone = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("barTextSize")
  private Integer barTextSize;
  public Integer getBarTextSize() { return barTextSize; }
  public CodeDesign setBarTextSize(Integer value) { this.barTextSize = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("finderColor")
  private String finderColor;
  public String getFinderColor() { return finderColor; }
  public CodeDesign setFinderColor(String value) { this.finderColor = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("finderInnerColor")
  private String finderInnerColor;
  public String getFinderInnerColor() { return finderInnerColor; }
  public CodeDesign setFinderInnerColor(String value) { this.finderInnerColor = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("gradientColor")
  private String gradientColor;
  public String getGradientColor() { return gradientColor; }
  public CodeDesign setGradientColor(String value) { this.gradientColor = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("frameColor")
  private String frameColor;
  public String getFrameColor() { return frameColor; }
  public CodeDesign setFrameColor(String value) { this.frameColor = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("captionColor")
  private String captionColor;
  public String getCaptionColor() { return captionColor; }
  public CodeDesign setCaptionColor(String value) { this.captionColor = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("headline")
  private String headline;
  public String getHeadline() { return headline; }
  public CodeDesign setHeadline(String value) { this.headline = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("subtitle")
  private String subtitle;
  public String getSubtitle() { return subtitle; }
  public CodeDesign setSubtitle(String value) { this.subtitle = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("schemaVersion")
  private Integer schemaVersion;
  public Integer getSchemaVersion() { return schemaVersion; }
  public CodeDesign setSchemaVersion(Integer value) { this.schemaVersion = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("template")
  private String template;
  public String getTemplate() { return template; }
  public CodeDesign setTemplate(String value) { this.template = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("templateVersion")
  private Integer templateVersion;
  public Integer getTemplateVersion() { return templateVersion; }
  public CodeDesign setTemplateVersion(Integer value) { this.templateVersion = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("frame")
  private String frame;
  public String getFrame() { return frame; }
  public CodeDesign setFrame(String value) { this.frame = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("caption")
  private String caption;
  public String getCaption() { return caption; }
  public CodeDesign setCaption(String value) { this.caption = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("logoImageId")
  private String logoImageId;
  public String getLogoImageId() { return logoImageId; }
  public CodeDesign setLogoImageId(String value) { this.logoImageId = value; return this; }
}
