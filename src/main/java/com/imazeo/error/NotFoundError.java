package com.imazeo.error;

/** HTTP 404. */
public final class NotFoundError extends ImazeoError {
  public NotFoundError(
      String message, Integer status, String code, String requestId, Object details) {
    super(message, status, code, requestId, details, null);
  }
}
