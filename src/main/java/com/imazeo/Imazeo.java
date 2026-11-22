package com.imazeo;

import com.imazeo.http.Transport;
import com.imazeo.resource.BarcodesResource;
import com.imazeo.resource.ContextResource;
import com.imazeo.resource.ImagesResource;
import com.imazeo.resource.CreditsResource;
import com.imazeo.resource.OperationsResource;
import com.imazeo.resource.OutputsResource;
import com.imazeo.resource.DynamicCodesResource;
import com.imazeo.resource.ArtifactsResource;
import com.imazeo.resource.ConversionsResource;
import com.imazeo.resource.PdfResource;
import com.imazeo.resource.QrResource;
import com.imazeo.resource.TransformsResource;
import java.util.List;
import java.util.Objects;

/**
 * Official Imazeo server-side client.
 *
 * <p>Uses secret {@code X-API-Key} authentication. Do not embed API keys in browsers.
 */
public final class Imazeo {
  private final Transport http;
  private final String configuredProjectId;
  private final Object projectLock = new Object();
  private volatile String resolvedProjectId;

  public final ContextResource context;
  public final ImagesResource images;
  public final TransformsResource transforms;
  public final QrResource qr;
  public final BarcodesResource barcodes;
  public final CreditsResource credits;
  public final OperationsResource operations;
  public final OutputsResource outputs;
  public final DynamicCodesResource dynamicCodes;
  public final ArtifactsResource artifacts;
  public final ConversionsResource conversions;
  public final PdfResource pdf;

  public Imazeo(Options options) {
    Objects.requireNonNull(options, "options");
    this.http = Transport.fromOptions(options);
    String pid = options.projectId();
    this.configuredProjectId =
        pid != null && !pid.trim().isEmpty() ? pid.trim() : null;
    this.context = new ContextResource(http);
    this.images = new ImagesResource(http, this::resolveProjectId);
    this.transforms = new TransformsResource(http, this::resolveProjectId);
    this.qr = new QrResource(http, this::resolveProjectId);
    this.barcodes = new BarcodesResource(http, this::resolveProjectId);
    this.credits = new CreditsResource(http, this::resolveProjectId);
    this.operations = new OperationsResource(http, this::resolveProjectId);
    this.outputs = new OutputsResource(http, this::resolveProjectId);
    this.dynamicCodes = new DynamicCodesResource(http, this::resolveProjectId);
    this.artifacts = new ArtifactsResource(http, this::resolveProjectId);
    this.conversions = new ConversionsResource(http, this::resolveProjectId);
    this.pdf = new PdfResource(http, this::resolveProjectId);
  }

  /** SDK package version (SemVer). */
  public static String version() {
    return Version.SDK_VERSION;
  }

  /** Default and supported API versions. */
  public static ApiCompatibility apiCompatibility() {
    return new ApiCompatibility(Version.DEFAULT_API_VERSION, Version.SUPPORTED_API_VERSIONS);
  }

  private String resolveProjectId() {
    if (configuredProjectId != null) {
      return configuredProjectId;
    }
    String cached = resolvedProjectId;
    if (cached != null) {
      return cached;
    }
    synchronized (projectLock) {
      if (resolvedProjectId == null) {
        resolvedProjectId = context.get().projectId();
      }
      return resolvedProjectId;
    }
  }

  /** Exposes transport for advanced/test use. */
  public Transport http() {
    return http;
  }

  public static final class ApiCompatibility {
    private final String defaultApi;
    private final List<String> supported;

    public ApiCompatibility(String defaultApi, List<String> supported) {
      this.defaultApi = defaultApi;
      this.supported = List.copyOf(supported);
    }

    public String defaultApi() {
      return defaultApi;
    }

    public List<String> supported() {
      return supported;
    }
  }
}
