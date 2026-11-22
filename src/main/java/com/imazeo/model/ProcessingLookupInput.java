package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ProcessingLookupInput {
  @com.fasterxml.jackson.annotation.JsonProperty("kind")
  private String kind;
  public String getKind() { return kind; }
  public ProcessingLookupInput setKind(String value) { this.kind = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("key")
  private String key;
  public String getKey() { return key; }
  public ProcessingLookupInput setKey(String value) { this.key = value; return this; }
}
