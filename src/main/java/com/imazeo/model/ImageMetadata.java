package com.imazeo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** Image metadata nested under {@link ImageRecord}. */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ImageMetadata {
  private String format;
  private String mimeType;
  private int width;
  private int height;
  private int frames;
  private long pixelCount;
  private boolean hasAlpha;
  private Integer orientation;

  public ImageMetadata() {}

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getFrames() {
    return frames;
  }

  public void setFrames(int frames) {
    this.frames = frames;
  }

  public long getPixelCount() {
    return pixelCount;
  }

  public void setPixelCount(long pixelCount) {
    this.pixelCount = pixelCount;
  }

  public boolean isHasAlpha() {
    return hasAlpha;
  }

  public void setHasAlpha(boolean hasAlpha) {
    this.hasAlpha = hasAlpha;
  }

  public Integer getOrientation() {
    return orientation;
  }

  public void setOrientation(Integer orientation) {
    this.orientation = orientation;
  }
}
