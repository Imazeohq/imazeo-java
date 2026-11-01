package com.imazeo.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Raw binary API response (image/PDF/QR/barcode content). */
public final class BinaryResult {
  private final byte[] data;
  private final String contentType;
  private final String requestId;
  private final Map<String, String> headers;
  private final String etag;
  private final Integer width;
  private final Integer height;
  private final String format;
  private final String transformationId;
  private final String cache;
  private final Integer pdfPage;
  private final boolean notModified;

  private BinaryResult(Builder b) {
    this.data = b.data != null ? b.data : new byte[0];
    this.contentType = b.contentType != null ? b.contentType : "application/octet-stream";
    this.requestId = b.requestId != null ? b.requestId : "";
    this.headers =
        b.headers != null
            ? Collections.unmodifiableMap(new LinkedHashMap<>(b.headers))
            : Map.of();
    this.etag = b.etag;
    this.width = b.width;
    this.height = b.height;
    this.format = b.format;
    this.transformationId = b.transformationId;
    this.cache = b.cache;
    this.pdfPage = b.pdfPage;
    this.notModified = b.notModified;
  }

  public static Builder builder() {
    return new Builder();
  }

  public byte[] data() {
    return data;
  }

  public String contentType() {
    return contentType;
  }

  public String requestId() {
    return requestId;
  }

  public Map<String, String> headers() {
    return headers;
  }

  public String etag() {
    return etag;
  }

  public Integer width() {
    return width;
  }

  public Integer height() {
    return height;
  }

  public String format() {
    return format;
  }

  public String transformationId() {
    return transformationId;
  }

  public String cache() {
    return cache;
  }

  public Integer pdfPage() {
    return pdfPage;
  }

  /** True when the server returned HTTP 304 for an If-None-Match request. */
  public boolean notModified() {
    return notModified;
  }

  public static final class Builder {
    private byte[] data;
    private String contentType;
    private String requestId;
    private Map<String, String> headers;
    private String etag;
    private Integer width;
    private Integer height;
    private String format;
    private String transformationId;
    private String cache;
    private Integer pdfPage;
    private boolean notModified;

    public Builder data(byte[] data) {
      this.data = data;
      return this;
    }

    public Builder contentType(String contentType) {
      this.contentType = contentType;
      return this;
    }

    public Builder requestId(String requestId) {
      this.requestId = requestId;
      return this;
    }

    public Builder headers(Map<String, String> headers) {
      this.headers = headers;
      return this;
    }

    public Builder etag(String etag) {
      this.etag = etag;
      return this;
    }

    public Builder width(Integer width) {
      this.width = width;
      return this;
    }

    public Builder height(Integer height) {
      this.height = height;
      return this;
    }

    public Builder format(String format) {
      this.format = format;
      return this;
    }

    public Builder transformationId(String transformationId) {
      this.transformationId = transformationId;
      return this;
    }

    public Builder cache(String cache) {
      this.cache = cache;
      return this;
    }

    public Builder pdfPage(Integer pdfPage) {
      this.pdfPage = pdfPage;
      return this;
    }

    public Builder notModified(boolean notModified) {
      this.notModified = notModified;
      return this;
    }

    public BinaryResult build() {
      return new BinaryResult(this);
    }
  }
}
