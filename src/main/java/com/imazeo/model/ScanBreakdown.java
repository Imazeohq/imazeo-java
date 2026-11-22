package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ScanBreakdown {
  @com.fasterxml.jackson.annotation.JsonProperty("key")
  private String key;
  public String getKey() { return key; }
  public ScanBreakdown setKey(String value) { this.key = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("count")
  private Integer count;
  public Integer getCount() { return count; }
  public ScanBreakdown setCount(Integer value) { this.count = value; return this; }
}
