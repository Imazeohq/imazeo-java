package com.imazeo.error;

/** Other 4xx/5xx with an Imazeo error envelope. */
public final class ApiError extends ImazeoError {
  public ApiError(
      String message, Integer status, String code, String requestId, Object details) {
    super(message, status, code, requestId, details, null);
  }
}
