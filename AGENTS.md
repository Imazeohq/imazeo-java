# imazeo — agent notes

Independent Maven package (`com.imazeo:imazeo`). Backend OpenAPI/`/v1` is the
contract source of truth. Follow workspace [docs/SDK_BEHAVIOR.md](../docs/SDK_BEHAVIOR.md)
and the TypeScript reference in `../sdk-typescript/`.
Do not add admin or JWT portal APIs to this client.
Publish only after the owner creates `Imazeohq/imazeo-java`, verifies Sonatype
namespace `com.imazeo`, and configures CI secrets — follow [RELEASE.md](RELEASE.md).
Never commit Central tokens or GPG private keys.
Do not run `mvn` or install JDK/Maven unless the user explicitly asks.
