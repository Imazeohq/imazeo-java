package com.imazeo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;

/** Transform operation specification. */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class TransformSpec {
  private Integer width;
  private Integer height;
  private String fit;
  private Map<String, Integer> crop;
  private String format;
  private Integer quality;
  private Integer rotate;
  private Boolean flip;
  private Boolean flop;
  private Boolean optimize;
  private Boolean stripMetadata;
  private Boolean grayscale;
  private Boolean negate;
  private Double blur;
  private Double sharpen;
  private Double brightness;
  private Double saturation;
  private Integer hue;
  private Object trim;
  private Map<String, Integer> extend;
  private String background;
  private String position;

  public Integer getWidth() {
    return width;
  }

  public TransformSpec setWidth(Integer width) {
    this.width = width;
    return this;
  }

  public Integer getHeight() {
    return height;
  }

  public TransformSpec setHeight(Integer height) {
    this.height = height;
    return this;
  }

  public String getFit() {
    return fit;
  }

  public TransformSpec setFit(String fit) {
    this.fit = fit;
    return this;
  }

  public Map<String, Integer> getCrop() {
    return crop;
  }

  public TransformSpec setCrop(Map<String, Integer> crop) {
    this.crop = crop;
    return this;
  }

  public String getFormat() {
    return format;
  }

  public TransformSpec setFormat(String format) {
    this.format = format;
    return this;
  }

  public Integer getQuality() {
    return quality;
  }

  public TransformSpec setQuality(Integer quality) {
    this.quality = quality;
    return this;
  }

  public Integer getRotate() {
    return rotate;
  }

  public TransformSpec setRotate(Integer rotate) {
    this.rotate = rotate;
    return this;
  }

  public Boolean getFlip() {
    return flip;
  }

  public TransformSpec setFlip(Boolean flip) {
    this.flip = flip;
    return this;
  }

  public Boolean getFlop() {
    return flop;
  }

  public TransformSpec setFlop(Boolean flop) {
    this.flop = flop;
    return this;
  }

  public Boolean getOptimize() {
    return optimize;
  }

  public TransformSpec setOptimize(Boolean optimize) {
    this.optimize = optimize;
    return this;
  }

  public Boolean getStripMetadata() {
    return stripMetadata;
  }

  public TransformSpec setStripMetadata(Boolean stripMetadata) {
    this.stripMetadata = stripMetadata;
    return this;
  }

  public Boolean getGrayscale() {
    return grayscale;
  }

  public TransformSpec setGrayscale(Boolean grayscale) {
    this.grayscale = grayscale;
    return this;
  }

  public Boolean getNegate() {
    return negate;
  }

  public TransformSpec setNegate(Boolean negate) {
    this.negate = negate;
    return this;
  }

  public Double getBlur() {
    return blur;
  }

  public TransformSpec setBlur(Double blur) {
    this.blur = blur;
    return this;
  }

  public Double getSharpen() {
    return sharpen;
  }

  public TransformSpec setSharpen(Double sharpen) {
    this.sharpen = sharpen;
    return this;
  }

  public Double getBrightness() {
    return brightness;
  }

  public TransformSpec setBrightness(Double brightness) {
    this.brightness = brightness;
    return this;
  }

  public Double getSaturation() {
    return saturation;
  }

  public TransformSpec setSaturation(Double saturation) {
    this.saturation = saturation;
    return this;
  }

  public Integer getHue() {
    return hue;
  }

  public TransformSpec setHue(Integer hue) {
    this.hue = hue;
    return this;
  }

  public Object getTrim() {
    return trim;
  }

  public TransformSpec setTrim(Object trim) {
    this.trim = trim;
    return this;
  }

  public Map<String, Integer> getExtend() {
    return extend;
  }

  public TransformSpec setExtend(Map<String, Integer> extend) {
    this.extend = extend;
    return this;
  }

  public String getBackground() {
    return background;
  }

  public TransformSpec setBackground(String background) {
    this.background = background;
    return this;
  }

  public String getPosition() {
    return position;
  }

  public TransformSpec setPosition(String position) {
    this.position = position;
    return this;
  }
}
