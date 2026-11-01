package com.imazeo.error;

/** Transport failure or timeout. */
public final class NetworkError extends ImazeoError {
  public NetworkError(String message, String requestId, Throwable cause) {
    super(message, null, null, requestId, null, cause);
  }

  public NetworkError(String message, Throwable cause) {
    this(message, null, cause);
  }

  public NetworkError(String message) {
    this(message, null, null);
  }
}
