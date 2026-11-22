package com.imazeo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ScanRecent {
  @com.fasterxml.jackson.annotation.JsonProperty("id")
  private String id;
  public String getId() { return id; }
  public ScanRecent setId(String value) { this.id = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("createdAt")
  private String createdAt;
  public String getCreatedAt() { return createdAt; }
  public ScanRecent setCreatedAt(String value) { this.createdAt = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("device")
  private String device;
  public String getDevice() { return device; }
  public ScanRecent setDevice(String value) { this.device = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("browser")
  private String browser;
  public String getBrowser() { return browser; }
  public ScanRecent setBrowser(String value) { this.browser = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("bot")
  private Boolean bot;
  public Boolean getBot() { return bot; }
  public ScanRecent setBot(Boolean value) { this.bot = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("country")
  private String country;
  public String getCountry() { return country; }
  public ScanRecent setCountry(String value) { this.country = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("ip")
  private String ip;
  public String getIp() { return ip; }
  public ScanRecent setIp(String value) { this.ip = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("forwardedFor")
  private String forwardedFor;
  public String getForwardedFor() { return forwardedFor; }
  public ScanRecent setForwardedFor(String value) { this.forwardedFor = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("userAgent")
  private String userAgent;
  public String getUserAgent() { return userAgent; }
  public ScanRecent setUserAgent(String value) { this.userAgent = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("acceptLanguage")
  private String acceptLanguage;
  public String getAcceptLanguage() { return acceptLanguage; }
  public ScanRecent setAcceptLanguage(String value) { this.acceptLanguage = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("language")
  private String language;
  public String getLanguage() { return language; }
  public ScanRecent setLanguage(String value) { this.language = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("referer")
  private String referer;
  public String getReferer() { return referer; }
  public ScanRecent setReferer(String value) { this.referer = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("platform")
  private String platform;
  public String getPlatform() { return platform; }
  public ScanRecent setPlatform(String value) { this.platform = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("clientHints")
  private String clientHints;
  public String getClientHints() { return clientHints; }
  public ScanRecent setClientHints(String value) { this.clientHints = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("mobileHint")
  private Boolean mobileHint;
  public Boolean getMobileHint() { return mobileHint; }
  public ScanRecent setMobileHint(Boolean value) { this.mobileHint = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("protocol")
  private String protocol;
  public String getProtocol() { return protocol; }
  public ScanRecent setProtocol(String value) { this.protocol = value; return this; }
  @com.fasterxml.jackson.annotation.JsonProperty("accept")
  private String accept;
  public String getAccept() { return accept; }
  public ScanRecent setAccept(String value) { this.accept = value; return this; }
}
