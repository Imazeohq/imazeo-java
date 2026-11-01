package com.imazeo.http;

import com.imazeo.model.PreparedUpload;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Manual multipart/form-data body for {@link java.net.http.HttpClient}. */
public final class MultipartBody {
  private final String boundary;
  private final byte[] body;

  private MultipartBody(String boundary, byte[] body) {
    this.boundary = boundary;
    this.body = body;
  }

  public String contentType() {
    return "multipart/form-data; boundary=" + boundary;
  }

  public byte[] body() {
    return body;
  }

  /** Alias for {@link #body()}. */
  public byte[] toByteArray() {
    return body;
  }

  public int contentLength() {
    return body.length;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private final String boundary =
        "----ImazeoFormBoundary" + UUID.randomUUID().toString().replace("-", "");
    private final List<Part> parts = new ArrayList<>();

    public Builder addFile(String fieldName, PreparedUpload prepared) {
      parts.add(
          new Part(
              fieldName,
              prepared.filename(),
              prepared.contentType(),
              prepared.bytes()));
      return this;
    }

    public Builder addFile(
        String fieldName, byte[] data, String filename, String contentType) {
      parts.add(new Part(fieldName, filename, contentType, data));
      return this;
    }

    public MultipartBody build() {
      try {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] crlf = "\r\n".getBytes(StandardCharsets.US_ASCII);
        for (Part part : parts) {
          out.write(("--" + boundary).getBytes(StandardCharsets.US_ASCII));
          out.write(crlf);
          String disposition =
              "Content-Disposition: form-data; name=\""
                  + part.fieldName
                  + "\"; filename=\""
                  + escapeFilename(part.filename)
                  + "\"";
          out.write(disposition.getBytes(StandardCharsets.US_ASCII));
          out.write(crlf);
          String ct =
              "Content-Type: "
                  + (part.contentType != null
                      ? part.contentType
                      : "application/octet-stream");
          out.write(ct.getBytes(StandardCharsets.US_ASCII));
          out.write(crlf);
          out.write(crlf);
          out.write(part.data);
          out.write(crlf);
        }
        out.write(("--" + boundary + "--").getBytes(StandardCharsets.US_ASCII));
        out.write(crlf);
        return new MultipartBody(boundary, out.toByteArray());
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    }

    private static String escapeFilename(String filename) {
      if (filename == null) return "file.bin";
      return filename.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static final class Part {
      final String fieldName;
      final String filename;
      final String contentType;
      final byte[] data;

      Part(String fieldName, String filename, String contentType, byte[] data) {
        this.fieldName = fieldName;
        this.filename = filename;
        this.contentType = contentType;
        this.data = data;
      }
    }
  }
}
