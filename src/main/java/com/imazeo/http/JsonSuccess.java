package com.imazeo.http;

/** Unwrapped success envelope: {@code { success, data, meta }}. */
public final class JsonSuccess<T> {
  private final T data;
  private final Meta meta;

  public JsonSuccess(T data, Meta meta) {
    this.data = data;
    this.meta = meta != null ? meta : Meta.empty();
  }

  public T data() {
    return data;
  }

  public Meta meta() {
    return meta;
  }

  public static final class Meta {
    private final String requestId;
    private final Integer page;
    private final Integer limit;
    private final Integer total;
    private final Integer pages;

    public Meta(
        String requestId, Integer page, Integer limit, Integer total, Integer pages) {
      this.requestId = requestId != null ? requestId : "";
      this.page = page;
      this.limit = limit;
      this.total = total;
      this.pages = pages;
    }

    public static Meta empty() {
      return new Meta("", null, null, null, null);
    }

    public String requestId() {
      return requestId;
    }

    public Integer page() {
      return page;
    }

    public Integer limit() {
      return limit;
    }

    public Integer total() {
      return total;
    }

    public Integer pages() {
      return pages;
    }
  }
}
