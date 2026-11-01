package com.imazeo.error;

/** HTTP 409. */
public final class ConflictError extends ImazeoError {
  public ConflictError(
      String message, Integer status, String code, String requestId, Object details) {
    super(message, status, code, requestId, details, null);
  }
}
