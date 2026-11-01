package com.imazeo.error;

/** Base typed error for Imazeo API and transport failures. */
public class ImazeoError extends RuntimeException {
  private final Integer status;
  private final String code;
  private final String requestId;
  private final Object details;

  public ImazeoError(
      String message, Integer status, String code, String requestId, Object details, Throwable cause) {
    super(message, cause);
    this.status = status;
    this.code = code;
    this.requestId = requestId;
    this.details = details;
  }

  public ImazeoError(String message) {
    this(message, null, null, null, null, null);
  }

  public Integer getStatus() {
    return status;
  }

  public String getCode() {
    return code;
  }

  public String getRequestId() {
    return requestId;
  }

  public Object getDetails() {
    return details;
  }
}
