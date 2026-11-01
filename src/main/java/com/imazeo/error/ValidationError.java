package com.imazeo.error;

/** HTTP 400 / 413 / 415 — invalid request. */
public final class ValidationError extends ImazeoError {
  public ValidationError(
      String message, Integer status, String code, String requestId, Object details) {
    super(message, status, code, requestId, details, null);
  }
}
