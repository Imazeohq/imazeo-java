# Changelog

## 1.0.0

### Added

- Initial official Java SDK (`com.imazeo:imazeo`)
- Resources: `context`, `images`, `transforms`, `qr`, `barcodes`, `pdf`
- Typed errors mapped from Imazeo API envelopes
- API version allowlist (`v1`) separate from SDK SemVer
- Multipart uploads with required `X-Upload-Length`
- Binary response helpers for images/PDF/QR/barcode
- Image list `q` filename-prefix filter
- Transform download `If-None-Match` → HTTP 304 as `BinaryResult.notModified`
- Transport via JDK `java.net.http.HttpClient` (Java 17+)
- Default client refuses redirects (`HttpClient.Redirect.NEVER`) so `X-API-Key` stays on the configured origin
- Reviewed API-key-only OpenAPI snapshot bundled as `openapi/backend.json`
- Native CI verification and independent SemVer/tag checks
- Maven Central publish profile (`-Pcentral-publish`: sources, javadoc, GPG, Portal plugin)

### Media contract

- Barcode: required `type` and `content`; optional `format` (`png`/`svg`) and `includeText`
- PDF merge uses repeated multipart `file` parts in order
- Integer model types for barcode scale and image hue where applicable

### Deferred (API-key routes exist; not in SDK_BEHAVIOR §1)

- Tool artifacts CRUD/download
- Project outputs list

### API compatibility

- Default API: `v1`
- Supported API versions: `v1`
- Default base URL: `https://api.imazeo.com`
