package com.imazeo.error;

/** Maps HTTP status codes to typed {@link ImazeoError} subclasses. */
public final class ErrorMapper {
  private ErrorMapper() {}

  public static ImazeoError mapHttpError(
      int status, String message, String code, String requestId, Object details) {
    String msg = message != null ? message : "Request failed";
    if (status == 401) {
      return new AuthenticationError(msg, status, code, requestId, details);
    }
    if (status == 403) {
      return new AuthorizationError(msg, status, code, requestId, details);
    }
    if (status == 404) {
      return new NotFoundError(msg, status, code, requestId, details);
    }
    if (status == 409) {
      return new ConflictError(msg, status, code, requestId, details);
    }
    if (status == 429) {
      return new RateLimitError(msg, status, code, requestId, details);
    }
    if (status == 400 || status == 413 || status == 415) {
      return new ValidationError(msg, status, code, requestId, details);
    }
    return new ApiError(msg, status, code, requestId, details);
  }
}
