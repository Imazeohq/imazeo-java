package com.imazeo.resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imazeo.http.JsonSuccess;
import com.imazeo.http.Transport;
import com.imazeo.model.BinaryResult;
import com.imazeo.model.MessageResult;
import com.imazeo.model.Page;
import com.imazeo.model.PageParams;
import com.imazeo.model.TransformationRecord;
import com.imazeo.model.TransformRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/** Project transforms resource. */
public final class TransformsResource {
  private final Transport http;
  private final Supplier<String> projectId;

  public TransformsResource(Transport http, Supplier<String> projectId) {
    this.http = http;
    this.projectId = projectId;
  }

  public BinaryResult create(TransformRequest request) {
    if (request.getImageId() == null && request.getUrl() == null) {
      throw new IllegalArgumentException("Exactly one of imageId or url is required");
    }
    if (request.getImageId() != null && request.getUrl() != null) {
      throw new IllegalArgumentException("Provide only one of imageId or url");
    }
    String pid = projectId.get();
    String url = http.path("projects", pid, "transforms");
    byte[] body;
    try {
      body = http.mapper().writeValueAsBytes(request);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to serialize transform request", e);
    }
    Map<String, String> headers = new LinkedHashMap<>();
    headers.put("Content-Type", "application/json");
    headers.put("Accept", "*/*");
    return http.requestBinary("POST", url, headers, body, null);
  }

  public Page<TransformationRecord> list() {
    return list(new PageParams());
  }

  public Page<TransformationRecord> list(PageParams params) {
    PageParams p = params != null ? params : new PageParams();
    String pid = projectId.get();
    Map<String, String> q = new LinkedHashMap<>();
    if (p.page() != null) q.put("page", String.valueOf(p.page()));
    if (p.limit() != null) q.put("limit", String.valueOf(p.limit()));
    String url = Transport.appendQuery(http.path("projects", pid, "transforms"), q);
    JsonSuccess<List<TransformationRecord>> res =
        http.requestJson(
            "GET",
            url,
            null,
            new TypeReference<List<TransformationRecord>>() {},
            null);
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

  public TransformationRecord get(String id) {
    String pid = projectId.get();
    String url = http.path("projects", pid, "transforms", id);
    return http.requestJson("GET", url, null, TransformationRecord.class, null).data();
  }

  public BinaryResult download(String id) {
    return download(id, null);
  }

  /** Download transform content; pass {@code ifNoneMatch} to receive 304 as {@code notModified}. */
  public BinaryResult download(String id, String ifNoneMatch) {
    String pid = projectId.get();
    String url = http.path("projects", pid, "transforms", id, "content");
    Map<String, String> headers = new LinkedHashMap<>();
    if (ifNoneMatch != null && !ifNoneMatch.isBlank()) {
      headers.put("If-None-Match", ifNoneMatch);
    }
    return http.requestBinary("GET", url, headers, null, null);
  }

  public MessageResult delete(String id) {
    String pid = projectId.get();
    String url = http.path("projects", pid, "transforms", id);
    return http.requestJson("DELETE", url, null, MessageResult.class, null).data();
  }
}
