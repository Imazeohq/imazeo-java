package com.imazeo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/** Pagination wrapper returned by list helpers. */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Page<T> {
  private final List<T> items;
  private final int page;
  private final int limit;
  private final int total;
  private final int pages;
  private final String requestId;

  public Page(List<T> items, int page, int limit, int total, int pages, String requestId) {
    this.items = items != null ? List.copyOf(items) : List.of();
    this.page = page;
    this.limit = limit;
    this.total = total;
    this.pages = pages;
    this.requestId = requestId != null ? requestId : "";
  }

  public List<T> items() {
    return items;
  }

  public int page() {
    return page;
  }

  public int limit() {
    return limit;
  }

  public int total() {
    return total;
  }

  public int pages() {
    return pages;
  }

  public String requestId() {
    return requestId;
  }
}
