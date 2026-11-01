package com.imazeo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** One page from a multi-page PDF render JSON response. */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PdfRenderPage {
  private int pageNumber;
  private double width;
  private double height;
  private String contentType;
  private String data;

  public PdfRenderPage() {}

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public double getWidth() {
    return width;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  /** Base64-encoded page payload. */
  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
