# imazeo (Java)

Official **server-side** Java SDK for Imazeo.

|                   |                                           |
| ----------------- | ----------------------------------------- |
| Maven coordinates | `com.imazeo:imazeo:1.0.0`                 |
| SDK version       | `1.0.0`                                   |
| Default API       | `v1`                                      |
| Supported APIs    | `v1`                                      |
| Runtime           | Java **17+** (`java.net.http.HttpClient`) |
| Registry          | [Maven Central](https://central.sonatype.com/artifact/com.imazeo/imazeo) |
| Source            | [GitHub](https://github.com/Imazeohq/imazeo-java) |

> **Security:** Secret API keys (`X-API-Key`) are for trusted servers only.
> Do **not** ship this client with a secret key in browsers or mobile apps.

Integration contract and learning path: [GUIDE.md](GUIDE.md).

## Install

Maven:

```xml
<dependency>
  <groupId>com.imazeo</groupId>
  <artifactId>imazeo</artifactId>
  <version>1.0.0</version>
</dependency>
```

Gradle:

```groovy
implementation 'com.imazeo:imazeo:1.0.0'
```

Requires Java 17+. Create a project API key in the Imazeo customer portal.
Default API origin is `https://api.imazeo.com` (override with `baseUrl` when needed).

## Learning path

Read the self-contained [integration guide](GUIDE.md) for project/key setup,
language examples, PDF response types, pagination, output retention, troubleshooting
and production practices.

The default JDK `HttpClient` refuses redirects (`HttpClient.Redirect.NEVER`) so
credentials stay on the configured API origin.

## Quick start

```java
import com.imazeo.Imazeo;
import com.imazeo.Options;
import com.imazeo.model.QrGenerateInput;
import java.nio.file.Files;
import java.nio.file.Path;

var client = new Imazeo(Options.builder()
    .apiKey(System.getenv("IMAZEO_API_KEY"))
    .baseUrl(System.getenv("IMAZEO_API_ORIGIN")) // supplied API origin
    // .projectId("…") // optional; otherwise resolved via /v1/api-key/context
    .build());

var qr = client.qr.generate(new QrGenerateInput("https://imazeo.com")
    .setFormat("png")
    .setSize(256));
Files.write(Path.of("qr.png"), qr.data());
```

## Authentication

```java
new Imazeo(Options.builder().apiKey(System.getenv("IMAZEO_API_KEY")).build());
```

Sends `X-API-Key` only. Never send `Authorization` together with an API key
(backend rejects that combination).

## Resources

| Resource            | Methods                                                                       |
| ------------------- | ----------------------------------------------------------------------------- |
| `client.context`    | `get()` → project/key environment                                             |
| `client.images`     | `upload`, `list` (`page`/`limit`/`q`), `get`, `delete`, `download`            |
| `client.transforms` | `create`, `list`, `get`, `download` (`ifNoneMatch` → `notModified`), `delete` |
| `client.qr`         | `generate`                                                                    |
| `client.barcodes`   | `generate`                                                                    |
| `client.pdf`        | `info`, `render`, `merge`, `extract`                                          |

### Upload + transform

```java
var image = client.images.upload(bytes, "photo.jpg", "image/jpeg");

var out = client.transforms.create(new TransformRequest()
    .setImageId(image.getId())
    .setMode("EPHEMERAL")
    .setTransform(new TransformSpec().setWidth(1200).setFormat("webp").setQuality(85)));
// out.data() → byte[]
```

### Remote URL transform

```java
var out = client.transforms.create(new TransformRequest()
    .setUrl("https://example.com/image.jpg")
    .setTransform(new TransformSpec().setWidth(640).setFormat("jpeg")));
```

### PDF

```java
var info = client.pdf.info(pdfBytes);
var page = client.pdf.render(UploadSource.of(pdfBytes), new PdfRenderOptions().pages("1").format("png"));
var merged = client.pdf.merge(List.of(UploadSource.of(pdfA), UploadSource.of(pdfB)));
var extracted = client.pdf.extract(UploadSource.of(pdfBytes), new PdfExtractOptions().pages("1,2"));
```

## Errors

```java
import com.imazeo.error.ImazeoError;
import com.imazeo.error.RateLimitError;

try {
  client.images.get(id);
} catch (RateLimitError error) {
  System.err.println(error.getCode() + " " + error.getRequestId());
} catch (ImazeoError error) {
  System.err.println(error.getStatus() + " " + error.getCode() + " " + error.getMessage());
}
```

## Configuration

| Option       | Default                  | Notes                                  |
| ------------ | ------------------------ | -------------------------------------- |
| `apiKey`     | required                 | Secret key                             |
| `projectId`  | from context             | Path project                           |
| `baseUrl`    | `https://api.imazeo.com` | Origin only; `/v1` stripped if present |
| `apiVersion` | `v1`                     | Allowlisted                            |
| `timeoutMs`  | `60000`                  | PDF uses 120s; QR/barcode/context 30s  |
| `appName`    | —                        | Appended to User-Agent                 |
| `httpClient` | JDK client, no redirects | Tests / custom agents                  |

User-Agent: `Imazeo-Java/1.0.0`.

## API version vs package version

- **API** path version: `v1` (configured via `apiVersion`)
- **SDK** SemVer: `pom.xml` → `1.0.0`

`Imazeo.apiCompatibility()` reports supported API versions.

## Not included

Portal JWT APIs (projects CRUD, API-key management, usage, plans, tickets, auth),
admin APIs, tool artifacts, project outputs, and browser secret-key usage.

## Development

```bash
mvn test
mvn package
```

OpenAPI sync notes: [`openapi/README.md`](openapi/README.md).

## License

MIT

## Media contract

Barcode generation requires `type` and string `content`; optional `format` is
`png` or `svg` (default `png`), and `includeText` is a boolean (default `true`).
PDF merge sends repeated `file` parts in order. Omit optional fields for defaults;
JSON numbers/booleans are not coerced and top-level optional nulls are rejected.
Barcode scale and image hue are integers. Methods retain language-specific names.
The reviewed API-key-only contract is bundled in `openapi/backend.json`.

```java
client.barcodes.generate(new BarcodeGenerateInput("code128", "IMAZEO-2026").setFormat("svg").setIncludeText(false));
```
