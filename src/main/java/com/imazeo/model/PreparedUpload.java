package com.imazeo.model;

import java.util.Objects;

/** Normalized upload payload ready for multipart. */
public final class PreparedUpload {
  private final byte[] bytes;
  private final String filename;
  private final String contentType;
  private final int byteLength;

  public PreparedUpload(byte[] bytes, String filename, String contentType) {
    this.bytes = Objects.requireNonNull(bytes, "bytes");
    this.filename = filename != null ? filename : "file.bin";
    this.contentType = contentType != null ? contentType : "application/octet-stream";
    this.byteLength = bytes.length;
  }

  public byte[] bytes() {
    return bytes;
  }

  public String filename() {
    return filename;
  }

  public String contentType() {
    return contentType;
  }

  public int byteLength() {
    return byteLength;
  }

  public static PreparedUpload prepare(UploadSource source) {
    return prepare(source, null, null);
  }

  public static PreparedUpload prepare(
      UploadSource source, String defaultFilename, String defaultContentType) {
    Objects.requireNonNull(source, "source");
    String filename =
        source.filename() != null
            ? source.filename()
            : (defaultFilename != null ? defaultFilename : "file.bin");
    String contentType =
        source.contentType() != null
            ? source.contentType()
            : (defaultContentType != null ? defaultContentType : "application/octet-stream");
    return new PreparedUpload(source.data(), filename, contentType);
  }

  public static PreparedUpload prepare(
      byte[] data, String defaultFilename, String defaultContentType) {
    return prepare(UploadSource.of(data), defaultFilename, defaultContentType);
  }
}
