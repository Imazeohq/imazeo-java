package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class OutputsSummary {
  @com.fasterxml.jackson.annotation.JsonProperty("total")
  private Integer total;
  public Integer getTotal() { return total; }
  public OutputsSummary setTotal(Integer value) { this.total = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("originals")
  private Integer originals;
  public Integer getOriginals() { return originals; }
  public OutputsSummary setOriginals(Integer value) { this.originals = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("transforms")
  private Integer transforms;
  public Integer getTransforms() { return transforms; }
  public OutputsSummary setTransforms(Integer value) { this.transforms = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("images")
  private Integer images;
  public Integer getImages() { return images; }
  public OutputsSummary setImages(Integer value) { this.images = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("qr")
  private Integer qr;
  public Integer getQr() { return qr; }
  public OutputsSummary setQr(Integer value) { this.qr = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("barcode")
  private Integer barcode;
  public Integer getBarcode() { return barcode; }
  public OutputsSummary setBarcode(Integer value) { this.barcode = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("pdf")
  private Integer pdf;
  public Integer getPdf() { return pdf; }
  public OutputsSummary setPdf(Integer value) { this.pdf = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("docx")
  private Integer docx;
  public Integer getDocx() { return docx; }
  public OutputsSummary setDocx(Integer value) { this.docx = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("xlsx")
  private Integer xlsx;
  public Integer getXlsx() { return xlsx; }
  public OutputsSummary setXlsx(Integer value) { this.xlsx = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("pptx")
  private Integer pptx;
  public Integer getPptx() { return pptx; }
  public OutputsSummary setPptx(Integer value) { this.pptx = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("website")
  private Integer website;
  public Integer getWebsite() { return website; }
  public OutputsSummary setWebsite(Integer value) { this.website = value; return this; }
}
