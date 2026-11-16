package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class DocxOptions {
  @com.fasterxml.jackson.annotation.JsonProperty("page")
  private Integer page;
  public Integer getPage() { return page; }
  public DocxOptions setPage(Integer value) { this.page = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("scale")
  private Double scale;
  public Double getScale() { return scale; }
  public DocxOptions setScale(Double value) { this.scale = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("format")
  private String format;
  public String getFormat() { return format; }
  public DocxOptions setFormat(String value) { this.format = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("quality")
  private Integer quality;
  public Integer getQuality() { return quality; }
  public DocxOptions setQuality(Integer value) { this.quality = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("fitSinglePage")
  private Boolean fitSinglePage;
  public Boolean getFitSinglePage() { return fitSinglePage; }
  public DocxOptions setFitSinglePage(Boolean value) { this.fitSinglePage = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("honorWordPageBreaks")
  private Boolean honorWordPageBreaks;
  public Boolean getHonorWordPageBreaks() { return honorWordPageBreaks; }
  public DocxOptions setHonorWordPageBreaks(Boolean value) { this.honorWordPageBreaks = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("renderHeaders")
  private Boolean renderHeaders;
  public Boolean getRenderHeaders() { return renderHeaders; }
  public DocxOptions setRenderHeaders(Boolean value) { this.renderHeaders = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("renderFooters")
  private Boolean renderFooters;
  public Boolean getRenderFooters() { return renderFooters; }
  public DocxOptions setRenderFooters(Boolean value) { this.renderFooters = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("renderFootnotes")
  private Boolean renderFootnotes;
  public Boolean getRenderFootnotes() { return renderFootnotes; }
  public DocxOptions setRenderFootnotes(Boolean value) { this.renderFootnotes = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("renderEndnotes")
  private Boolean renderEndnotes;
  public Boolean getRenderEndnotes() { return renderEndnotes; }
  public DocxOptions setRenderEndnotes(Boolean value) { this.renderEndnotes = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("repairBrokenRelationships")
  private Boolean repairBrokenRelationships;
  public Boolean getRepairBrokenRelationships() { return repairBrokenRelationships; }
  public DocxOptions setRepairBrokenRelationships(Boolean value) { this.repairBrokenRelationships = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("unwrapSdt")
  private Boolean unwrapSdt;
  public Boolean getUnwrapSdt() { return unwrapSdt; }
  public DocxOptions setUnwrapSdt(Boolean value) { this.unwrapSdt = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("collapseMcAlternate")
  private Boolean collapseMcAlternate;
  public Boolean getCollapseMcAlternate() { return collapseMcAlternate; }
  public DocxOptions setCollapseMcAlternate(Boolean value) { this.collapseMcAlternate = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("ignoreImageWrap")
  private Boolean ignoreImageWrap;
  public Boolean getIgnoreImageWrap() { return ignoreImageWrap; }
  public DocxOptions setIgnoreImageWrap(Boolean value) { this.ignoreImageWrap = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("ignoreTableWrap")
  private Boolean ignoreTableWrap;
  public Boolean getIgnoreTableWrap() { return ignoreTableWrap; }
  public DocxOptions setIgnoreTableWrap(Boolean value) { this.ignoreTableWrap = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("applyWordChrome")
  private Boolean applyWordChrome;
  public Boolean getApplyWordChrome() { return applyWordChrome; }
  public DocxOptions setApplyWordChrome(Boolean value) { this.applyWordChrome = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("normalizeTheme")
  private Boolean normalizeTheme;
  public Boolean getNormalizeTheme() { return normalizeTheme; }
  public DocxOptions setNormalizeTheme(Boolean value) { this.normalizeTheme = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("renderChanges")
  private Boolean renderChanges;
  public Boolean getRenderChanges() { return renderChanges; }
  public DocxOptions setRenderChanges(Boolean value) { this.renderChanges = value; return this; }
}
