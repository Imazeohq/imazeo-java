package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CreditQuoteInput {
  @com.fasterxml.jackson.annotation.JsonProperty("kind")
  private String kind;
  public String getKind() { return kind; }
  public CreditQuoteInput setKind(String value) { this.kind = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("units")
  private Integer units;
  public Integer getUnits() { return units; }
  public CreditQuoteInput setUnits(Integer value) { this.units = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("remote")
  private Boolean remote;
  public Boolean getRemote() { return remote; }
  public CreditQuoteInput setRemote(Boolean value) { this.remote = value; return this; }
}
