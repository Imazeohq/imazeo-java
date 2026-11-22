package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CreditQuote {
  @com.fasterxml.jackson.annotation.JsonProperty("replayEnabled")
  private Boolean replayEnabled;
  public Boolean getReplayEnabled() { return replayEnabled; }
  public CreditQuote setReplayEnabled(Boolean value) { this.replayEnabled = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("id")
  private String id;
  public String getId() { return id; }
  public CreditQuote setId(String value) { this.id = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("kind")
  private String kind;
  public String getKind() { return kind; }
  public CreditQuote setKind(String value) { this.kind = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("units")
  private Integer units;
  public Integer getUnits() { return units; }
  public CreditQuote setUnits(Integer value) { this.units = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("credits")
  private Integer credits;
  public Integer getCredits() { return credits; }
  public CreditQuote setCredits(Integer value) { this.credits = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("unitCost")
  private Integer unitCost;
  public Integer getUnitCost() { return unitCost; }
  public CreditQuote setUnitCost(Integer value) { this.unitCost = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("remoteUnitCost")
  private Integer remoteUnitCost;
  public Integer getRemoteUnitCost() { return remoteUnitCost; }
  public CreditQuote setRemoteUnitCost(Integer value) { this.remoteUnitCost = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("scheduleId")
  private String scheduleId;
  public String getScheduleId() { return scheduleId; }
  public CreditQuote setScheduleId(String value) { this.scheduleId = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("expiresAt")
  private String expiresAt;
  public String getExpiresAt() { return expiresAt; }
  public CreditQuote setExpiresAt(String value) { this.expiresAt = value; return this; }
}
