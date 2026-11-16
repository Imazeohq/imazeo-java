package com.imazeo.model;
import java.util.Map;
public record ArtifactSaveInput(String kind, String displayName, String contentType, Map<String, Object> requestSnapshot, String sourceImageId) {}
