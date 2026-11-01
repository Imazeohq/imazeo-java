package com.imazeo.error;

/** HTTP 401 — invalid or missing API key. */
public final class AuthenticationError extends ImazeoError {
  public AuthenticationError(
      String message, Integer status, String code, String requestId, Object details) {
    super(message, status, code, requestId, details, null);
  }
}
