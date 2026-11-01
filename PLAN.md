# SDK-6 plan — Java

**Status: implemented (1.0.0)**

1. Maven coordinates `com.imazeo:imazeo:1.0.0`, Java 17 — done
2. JDK `HttpClient` transport (multipart + `X-Upload-Length`, JSON unwrap, 304) — done
3. Full resource surface matching TypeScript / `docs/SDK_BEHAVIOR.md` — done
4. Typed errors + `ErrorMapper` — done
5. JUnit 5 + MockWebServer tests — done
6. Default client refuses redirects — done
7. Central publish profile + CI publish workflow — ready

Scope: API-key resources only. No admin/JWT/tool-artifacts/outputs.

Reference: `../sdk-typescript/` and `../docs/SDK_BEHAVIOR.md`.
Publish by tagging `v1.0.0` on `Imazeohq/imazeo-java` after Sonatype verifies
namespace `com.imazeo`, then run the publish workflow.
