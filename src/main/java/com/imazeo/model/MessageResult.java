package com.imazeo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** Simple message payload from delete endpoints. */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class MessageResult {
  private String message;

  public MessageResult() {}

  public MessageResult(String message) {
    this.message = message;
  }

  public String message() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
