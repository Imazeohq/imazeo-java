# SDK release procedure (`com.imazeo:imazeo` on Maven Central)

Independent repository: [Imazeohq/imazeo-java](https://github.com/Imazeohq/imazeo-java).  
Coordinates: [`com.imazeo:imazeo`](https://central.sonatype.com/artifact/com.imazeo/imazeo).  
Supported API prefix: `v1`. SemVer lives in `pom.xml`, `Version.SDK_VERSION`, and `package.json`
(kept in sync by the release script). Published from git tag `v*`.

Never overwrite a published version; ship a patch instead.

## One-time: Maven Central secrets

GitHub → **Settings → Secrets** for `Imazeohq/imazeo-java`:

| Secret | Value |
| --- | --- |
| `CENTRAL_USERNAME` | Maven Central Portal token username |
| `CENTRAL_PASSWORD` | Maven Central Portal token password |
| `GPG_PRIVATE_KEY` | ASCII-armored signing private key |
| `GPG_PASSPHRASE` | Signing key passphrase |

## Release (PowerShell)

Commit your work on `main` first (clean working tree required). One-time: `npm install`.

```powershell
$env:RELEASE_MODE = "true"
npm run release
```

You will be prompted for version bump and GitHub Release.  
This syncs versions, commits, tags `vX.Y.Z`, and pushes.  
**Publish Maven Central** then deploys with the secrets above.

## Release from GitHub only

If the version bump is already on `main`:

1. **Releases** → Draft a new release → tag `vX.Y.Z` → Publish  
   or
2. **Actions** → **Publish Maven Central** → **Run workflow**

## After publish

Verify: https://central.sonatype.com/artifact/com.imazeo/imazeo
