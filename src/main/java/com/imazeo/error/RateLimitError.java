package com.imazeo.error;

/** HTTP 429. */
public final class RateLimitError extends ImazeoError {
  public RateLimitError(
      String message, Integer status, String code, String requestId, Object details) {
    super(message, status, code, requestId, details, null);
  }
}
