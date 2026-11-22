package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ScanPoint {
  @com.fasterxml.jackson.annotation.JsonProperty("date")
  private String date;
  public String getDate() { return date; }
  public ScanPoint setDate(String value) { this.date = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("count")
  private Integer count;
  public Integer getCount() { return count; }
  public ScanPoint setCount(Integer value) { this.count = value; return this; }
}
