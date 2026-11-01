package com.imazeo.model;

/** Pagination and optional filename-prefix filter. */
public final class PageParams {
  private final Integer page;
  private final Integer limit;
  private final String q;

  public PageParams() {
    this(null, null, null);
  }

  public PageParams(Integer page, Integer limit, String q) {
    this.page = page;
    this.limit = limit;
    this.q = q;
  }

  private PageParams(Builder builder) {
    this.page = builder.page;
    this.limit = builder.limit;
    this.q = builder.q;
  }

  public static PageParams of(int page, int limit) {
    return new PageParams(page, limit, null);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Integer page() {
    return page;
  }

  public Integer limit() {
    return limit;
  }

  /** Filename / display-name prefix filter where the resource supports it. */
  public String q() {
    return q;
  }

  public PageParams withPage(Integer page) {
    return new PageParams(page, this.limit, this.q);
  }

  public PageParams withLimit(Integer limit) {
    return new PageParams(this.page, limit, this.q);
  }

  public PageParams withQuery(String q) {
    return new PageParams(this.page, this.limit, q);
  }

  public static final class Builder {
    private Integer page;
    private Integer limit;
    private String q;

    public Builder page(Integer page) {
      this.page = page;
      return this;
    }

    public Builder limit(Integer limit) {
      this.limit = limit;
      return this;
    }

    public Builder q(String q) {
      this.q = q;
      return this;
    }

    public PageParams build() {
      return new PageParams(this);
    }
  }
}
