package com.imazeo.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.imazeo.Version;
import com.imazeo.http.JsonSuccess;
import com.imazeo.http.MultipartBody;
import com.imazeo.http.Transport;
import com.imazeo.model.BinaryResult;
import com.imazeo.model.PdfExtractOptions;
import com.imazeo.model.PdfInfo;
import com.imazeo.model.PdfRenderOptions;
import com.imazeo.model.PdfRenderPage;
import com.imazeo.model.PdfRenderResult;
import com.imazeo.model.PreparedUpload;
import com.imazeo.model.UploadSource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/** PDF tools resource. */
public final class PdfResource {
  private final Transport http;
  private final Supplier<String> projectId;

  public PdfResource(Transport http, Supplier<String> projectId) {
    this.http = http;
    this.projectId = projectId;
  }

  public PdfInfo info(byte[] data) {
    return info(UploadSource.of(data), null);
  }

  public PdfInfo info(UploadSource source) {
    return info(source, null);
  }

  public PdfInfo info(UploadSource source, String filename) {
    String pid = projectId.get();
    PreparedUpload prepared =
        PreparedUpload.prepare(
            source, filename != null ? filename : "document.pdf", "application/pdf");
    MultipartBody form = MultipartBody.builder().addFile("file", prepared).build();
    String url = http.path("projects", pid, "tools", "pdf", "info");
    return http.requestMultipartJson(
            "POST", url, form, null, PdfInfo.class, Version.PDF_TIMEOUT_MS)
        .data();
  }

  public PdfRenderResult render(byte[] data) {
    return render(UploadSource.of(data), new PdfRenderOptions());
  }

  public PdfRenderResult render(UploadSource source, PdfRenderOptions options) {
    PdfRenderOptions opts = options != null ? options : new PdfRenderOptions();
    String pid = projectId.get();
    PreparedUpload prepared =
        PreparedUpload.prepare(
            source,
            opts.filename() != null ? opts.filename() : "document.pdf",
            "application/pdf");
    MultipartBody form = MultipartBody.builder().addFile("file", prepared).build();
    Map<String, String> q = new LinkedHashMap<>();
    if (opts.pages() != null) q.put("pages", opts.pages());
    if (opts.rangeStart() != null) q.put("rangeStart", String.valueOf(opts.rangeStart()));
    if (opts.rangeEnd() != null) q.put("rangeEnd", String.valueOf(opts.rangeEnd()));
    if (opts.all() != null) q.put("all", String.valueOf(opts.all()));
    if (opts.format() != null) q.put("format", opts.format());
    if (opts.scale() != null) q.put("scale", String.valueOf(opts.scale()));
    if (opts.quality() != null) q.put("quality", String.valueOf(opts.quality()));
    String url =
        Transport.appendQuery(http.path("projects", pid, "tools", "pdf", "render"), q);
    Object result =
        http.requestMultipartBinary("POST", url, form, null, Version.PDF_TIMEOUT_MS);
    if (result instanceof BinaryResult binary) {
      return PdfRenderResult.binary(binary);
    }
    @SuppressWarnings("unchecked")
    JsonSuccess<JsonNode> json = (JsonSuccess<JsonNode>) result;
    List<PdfRenderPage> pages = new ArrayList<>();
    JsonNode data = json.data();
    if (data != null && data.has("pages") && data.get("pages").isArray()) {
      for (JsonNode node : data.get("pages")) {
        pages.add(http.mapper().convertValue(node, PdfRenderPage.class));
      }
    }
    return PdfRenderResult.pages(pages, json.meta().requestId());
  }

  public BinaryResult merge(List<UploadSource> sources) {
    if (sources == null || sources.isEmpty()) {
      throw new IllegalArgumentException("At least one PDF is required");
    }
    String pid = projectId.get();
    MultipartBody.Builder form = MultipartBody.builder();
    for (int i = 0; i < sources.size(); i++) {
      PreparedUpload prepared =
          PreparedUpload.prepare(
              sources.get(i), "file-" + (i + 1) + ".pdf", "application/pdf");
      form.addFile("file", prepared);
    }
    String url = http.path("projects", pid, "tools", "pdf", "merge");
    Object result =
        http.requestMultipartBinary("POST", url, form.build(), null, Version.PDF_TIMEOUT_MS);
    if (!(result instanceof BinaryResult binary)) {
      throw new IllegalStateException("Expected PDF binary response from merge");
    }
    return binary;
  }

  public BinaryResult extract(UploadSource source, PdfExtractOptions options) {
    if (options == null) {
      throw new IllegalArgumentException("extract options are required");
    }
    String pid = projectId.get();
    PreparedUpload prepared =
        PreparedUpload.prepare(
            source,
            options.filename() != null ? options.filename() : "document.pdf",
            "application/pdf");
    MultipartBody form = MultipartBody.builder().addFile("file", prepared).build();
    Map<String, String> q = new LinkedHashMap<>();
    if (options.pages() != null) q.put("pages", options.pages());
    if (options.rangeStart() != null) q.put("rangeStart", String.valueOf(options.rangeStart()));
    if (options.rangeEnd() != null) q.put("rangeEnd", String.valueOf(options.rangeEnd()));
    String url =
        Transport.appendQuery(http.path("projects", pid, "tools", "pdf", "extract"), q);
    Object result =
        http.requestMultipartBinary("POST", url, form, null, Version.PDF_TIMEOUT_MS);
    if (!(result instanceof BinaryResult binary)) {
      throw new IllegalStateException("Expected PDF binary response from extract");
    }
    return binary;
  }
}
