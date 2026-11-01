package com.imazeo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

/** PDF info response. */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PdfInfo {
  private int pageCount;
  private boolean encrypted;
  private List<PdfPageInfo> pages = new ArrayList<>();

  public PdfInfo() {}

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public boolean isEncrypted() {
    return encrypted;
  }

  public void setEncrypted(boolean encrypted) {
    this.encrypted = encrypted;
  }

  public List<PdfPageInfo> getPages() {
    return pages;
  }

  public void setPages(List<PdfPageInfo> pages) {
    this.pages = pages != null ? pages : new ArrayList<>();
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static final class PdfPageInfo {
    private int pageNumber;
    private double width;
    private double height;

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
  }
}
