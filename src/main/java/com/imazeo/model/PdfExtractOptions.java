package com.imazeo.model;

/** Options for PDF extract. */
public final class PdfExtractOptions {
  private String pages;
  private Integer rangeStart;
  private Integer rangeEnd;
  private String filename;

  public String pages() {
    return pages;
  }

  public PdfExtractOptions pages(String pages) {
    this.pages = pages;
    return this;
  }

  public Integer rangeStart() {
    return rangeStart;
  }

  public PdfExtractOptions rangeStart(Integer rangeStart) {
    this.rangeStart = rangeStart;
    return this;
  }

  public Integer rangeEnd() {
    return rangeEnd;
  }

  public PdfExtractOptions rangeEnd(Integer rangeEnd) {
    this.rangeEnd = rangeEnd;
    return this;
  }

  public String filename() {
    return filename;
  }

  public PdfExtractOptions filename(String filename) {
    this.filename = filename;
    return this;
  }
}
