# imazeo — agent notes

Independent public Maven package (`com.imazeo:imazeo`).

- Contract: published API `v1` / OpenAPI snapshot in [`openapi/backend.json`](openapi/backend.json)
- Scope: project API-key context, media, credit quotes, operations, conversions, dynamic QR, artifacts and outputs
- Do not add admin or customer JWT portal APIs
- Do not publish without maintainer approval — see [RELEASE.md](RELEASE.md)
- Never commit Central tokens or GPG private keys
- Release: `$env:RELEASE_MODE="true"; npm run release` (syncs `pom.xml` + `Version.java`); Actions publishes to Maven Central
