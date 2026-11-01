package com.imazeo.model;

/** Options for PDF render. */
public final class PdfRenderOptions {
  private String pages;
  private Integer rangeStart;
  private Integer rangeEnd;
  private Boolean all;
  private String format;
  private Double scale;
  private Integer quality;
  private String filename;

  public String pages() {
    return pages;
  }

  public PdfRenderOptions pages(String pages) {
    this.pages = pages;
    return this;
  }

  public Integer rangeStart() {
    return rangeStart;
  }

  public PdfRenderOptions rangeStart(Integer rangeStart) {
    this.rangeStart = rangeStart;
    return this;
  }

  public Integer rangeEnd() {
    return rangeEnd;
  }

  public PdfRenderOptions rangeEnd(Integer rangeEnd) {
    this.rangeEnd = rangeEnd;
    return this;
  }

  public Boolean all() {
    return all;
  }

  public PdfRenderOptions all(Boolean all) {
    this.all = all;
    return this;
  }

  public String format() {
    return format;
  }

  public PdfRenderOptions format(String format) {
    this.format = format;
    return this;
  }

  public Double scale() {
    return scale;
  }

  public PdfRenderOptions scale(Double scale) {
    this.scale = scale;
    return this;
  }

  public Integer quality() {
    return quality;
  }

  public PdfRenderOptions quality(Integer quality) {
    this.quality = quality;
    return this;
  }

  public String filename() {
    return filename;
  }

  public PdfRenderOptions filename(String filename) {
    this.filename = filename;
    return this;
  }
}
