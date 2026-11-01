package com.imazeo.resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imazeo.http.JsonSuccess;
import com.imazeo.http.MultipartBody;
import com.imazeo.http.Transport;
import com.imazeo.model.BinaryResult;
import com.imazeo.model.ImageRecord;
import com.imazeo.model.MessageResult;
import com.imazeo.model.Page;
import com.imazeo.model.PageParams;
import com.imazeo.model.PreparedUpload;
import com.imazeo.model.UploadSource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/** Project images resource. */
public final class ImagesResource {
  private final Transport http;
  private final Supplier<String> projectId;

  public ImagesResource(Transport http, Supplier<String> projectId) {
    this.http = http;
    this.projectId = projectId;
  }

  public ImageRecord upload(byte[] data) {
    return upload(UploadSource.of(data), null, null);
  }

  public ImageRecord upload(byte[] data, String filename, String contentType) {
    return upload(UploadSource.of(data, filename, contentType), filename, contentType);
  }

  public ImageRecord upload(UploadSource source) {
    return upload(source, null, null);
  }

  public ImageRecord upload(UploadSource source, String filename, String contentType) {
    String pid = projectId.get();
    PreparedUpload prepared = PreparedUpload.prepare(source, filename, contentType);
    MultipartBody form = MultipartBody.builder().addFile("file", prepared).build();
    String url = http.path("projects", pid, "images");
    return http.requestMultipartJson(
            "POST", url, form, prepared.byteLength(), ImageRecord.class, null)
        .data();
  }

  public Page<ImageRecord> list() {
    return list(new PageParams());
  }

  public Page<ImageRecord> list(PageParams params) {
    PageParams p = params != null ? params : new PageParams();
    String pid = projectId.get();
    Map<String, String> q = new LinkedHashMap<>();
    if (p.page() != null) q.put("page", String.valueOf(p.page()));
    if (p.limit() != null) q.put("limit", String.valueOf(p.limit()));
    if (p.q() != null && !p.q().isEmpty()) q.put("q", p.q());
    String url = Transport.appendQuery(http.path("projects", pid, "images"), q);
    JsonSuccess<List<ImageRecord>> res =
        http.requestJson(
            "GET", url, null, new TypeReference<List<ImageRecord>>() {}, null);
    return new Page<>(
        res.data() != null ? res.data() : List.of(),
        res.meta().page() != null
            ? res.meta().page()
            : (p.page() != null ? p.page() : 1),
        res.meta().limit() != null
            ? res.meta().limit()
            : (p.limit() != null ? p.limit() : 20),
        res.meta().total() != null
            ? res.meta().total()
            : (res.data() != null ? res.data().size() : 0),
        res.meta().pages() != null ? res.meta().pages() : 1,
        res.meta().requestId());
  }

  public ImageRecord get(String id) {
    String pid = projectId.get();
    String url = http.path("projects", pid, "images", id);
    return http.requestJson("GET", url, null, ImageRecord.class, null).data();
  }

  public MessageResult delete(String id) {
    String pid = projectId.get();
    String url = http.path("projects", pid, "images", id);
    return http.requestJson("DELETE", url, null, MessageResult.class, null).data();
  }

  public BinaryResult download(String id) {
    String pid = projectId.get();
    String url = http.path("projects", pid, "images", id, "content");
    return http.requestBinary("GET", url, null, null, null);
  }
}
