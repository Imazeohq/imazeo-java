package com.imazeo;

import java.util.List;

/**
 * SDK and API version constants.
 *
 * <p>SDK SemVer is independent of the backend path API version ({@code v1}).
 */
public final class Version {
  public static final String SDK_VERSION = "1.0.0";
  public static final String DEFAULT_API_VERSION = "v1";
  public static final List<String> SUPPORTED_API_VERSIONS = List.of("v1");
  public static final String DEFAULT_BASE_URL = "https://api.imazeo.com";
  public static final long DEFAULT_TIMEOUT_MS = 60_000L;
  public static final long PDF_TIMEOUT_MS = 120_000L;
  public static final long LIGHT_TIMEOUT_MS = 30_000L;

  private Version() {}
}
