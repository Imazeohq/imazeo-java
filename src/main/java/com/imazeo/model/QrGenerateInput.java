package com.imazeo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/** QR generation request body. */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class QrGenerateInput {
  private String content;
  private String format;
  private Integer size;
  private Integer margin;
  private String errorCorrection;
  private String foreground;
  private String background;

  public QrGenerateInput() {}

  public QrGenerateInput(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public QrGenerateInput setContent(String content) {
    this.content = content;
    return this;
  }

  public String getFormat() {
    return format;
  }

  public QrGenerateInput setFormat(String format) {
    this.format = format;
    return this;
  }

  public Integer getSize() {
    return size;
  }

  public QrGenerateInput setSize(Integer size) {
    this.size = size;
    return this;
  }

  public Integer getMargin() {
    return margin;
  }

  public QrGenerateInput setMargin(Integer margin) {
    this.margin = margin;
    return this;
  }

  public String getErrorCorrection() {
    return errorCorrection;
  }

  public QrGenerateInput setErrorCorrection(String errorCorrection) {
    this.errorCorrection = errorCorrection;
    return this;
  }

  public String getForeground() {
    return foreground;
  }

  public QrGenerateInput setForeground(String foreground) {
    this.foreground = foreground;
    return this;
  }

  public String getBackground() {
    return background;
  }

  public QrGenerateInput setBackground(String background) {
    this.background = background;
    return this;
  }
}
