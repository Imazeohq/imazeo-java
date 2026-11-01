package com.imazeo.error;

/** HTTP 403 — plan, subscription, or feature gate. */
public final class AuthorizationError extends ImazeoError {
  public AuthorizationError(
      String message, Integer status, String code, String requestId, Object details) {
    super(message, status, code, requestId, details, null);
  }
}
