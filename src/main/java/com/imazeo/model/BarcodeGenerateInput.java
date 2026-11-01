package com.imazeo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/** Barcode generation request body. */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class BarcodeGenerateInput {
  private String type;
  private String content;
  private String format;
  private Integer scale;
  private Integer height;
  private Boolean includeText;
  private String foreground;
  private String background;

  public BarcodeGenerateInput() {}

  public BarcodeGenerateInput(String type, String content) {
    this.type = type;
    this.content = content;
  }

  public String getType() {
    return type;
  }

  public BarcodeGenerateInput setType(String type) {
    this.type = type;
    return this;
  }

  public String getContent() {
    return content;
  }

  public BarcodeGenerateInput setContent(String content) {
    this.content = content;
    return this;
  }

  public String getFormat() {
    return format;
  }

  public BarcodeGenerateInput setFormat(String format) {
    this.format = format;
    return this;
  }

  public Integer getScale() {
    return scale;
  }

  public BarcodeGenerateInput setScale(Integer scale) {
    this.scale = scale;
    return this;
  }

  public Integer getHeight() {
    return height;
  }

  public BarcodeGenerateInput setHeight(Integer height) {
    this.height = height;
    return this;
  }

  public Boolean getIncludeText() {
    return includeText;
  }

  public BarcodeGenerateInput setIncludeText(Boolean includeText) {
    this.includeText = includeText;
    return this;
  }

  public String getForeground() {
    return foreground;
  }

  public BarcodeGenerateInput setForeground(String foreground) {
    this.foreground = foreground;
    return this;
  }

  public String getBackground() {
    return background;
  }

  public BarcodeGenerateInput setBackground(String background) {
    this.background = background;
    return this;
  }
}
