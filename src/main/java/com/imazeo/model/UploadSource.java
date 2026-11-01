package com.imazeo.model;

/** Local file bytes for multipart uploads (never sent as a filesystem path string). */
public final class UploadSource {
  private final byte[] data;
  private final String filename;
  private final String contentType;

  public UploadSource(byte[] data) {
    this(data, null, null);
  }

  public UploadSource(byte[] data, String filename, String contentType) {
    if (data == null) {
      throw new IllegalArgumentException("Upload data is required");
    }
    this.data = data;
    this.filename = filename;
    this.contentType = contentType;
  }

  public static UploadSource of(byte[] data) {
    return new UploadSource(data);
  }

  public static UploadSource of(byte[] data, String filename, String contentType) {
    return new UploadSource(data, filename, contentType);
  }

  public byte[] data() {
    return data;
  }

  public String filename() {
    return filename;
  }

  public String contentType() {
    return contentType;
  }
}
