package com.imazeo.model;

import java.util.List;

/**
 * Result of PDF render: either raw image bytes (single page) or JSON multi-page payloads.
 */
public final class PdfRenderResult {
  private final BinaryResult binary;
  private final List<PdfRenderPage> pages;
  private final String requestId;

  private PdfRenderResult(BinaryResult binary, List<PdfRenderPage> pages, String requestId) {
    this.binary = binary;
    this.pages = pages;
    this.requestId = requestId != null ? requestId : "";
  }

  public static PdfRenderResult binary(BinaryResult binary) {
    return new PdfRenderResult(binary, null, binary != null ? binary.requestId() : "");
  }

  public static PdfRenderResult pages(List<PdfRenderPage> pages, String requestId) {
    return new PdfRenderResult(null, pages != null ? List.copyOf(pages) : List.of(), requestId);
  }

  public boolean isBinary() {
    return binary != null;
  }

  public BinaryResult binary() {
    return binary;
  }

  public List<PdfRenderPage> pages() {
    return pages;
  }

  public String requestId() {
    return requestId;
  }
}
