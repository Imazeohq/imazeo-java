# SDK release procedure (`com.imazeo:imazeo` on Maven Central)

Independent repository: [Imazeohq/imazeo-java](https://github.com/Imazeohq/imazeo-java).  
Coordinates: [`com.imazeo:imazeo`](https://central.sonatype.com/artifact/com.imazeo/imazeo).  
Supported API prefix: `v1`. SemVer lives in this repo (`pom.xml` + `Version.SDK_VERSION`)
and is published from git tag `v1.0.0` plus Maven Central Portal deploy.

Do not publish automatically from CI until secrets and namespace ownership are ready.
Never overwrite a published version; ship a patch instead.

## Pre-publish checks (GitHub Actions CI)

```text
python3 scripts/check-release.py
mvn --batch-mode verify
```

Local JDK/Maven are optional; prefer CI on `Imazeohq/imazeo-java`.

## Manual publish (first release `1.0.0`)

1. Create empty public GitHub repo `Imazeohq/imazeo-java` and push this tree to `main`.
2. Create a [Maven Central Portal](https://central.sonatype.com) account and **claim namespace `com.imazeo`**
   (DNS / GitHub verification as required by Sonatype). Wait until the namespace is verified.
3. Generate a Central Portal **user token** (username + password pair for Maven `settings.xml` server `central`).
4. Create a **GPG key** for signing (export private key for CI). Publish the public key to a keyserver
   (e.g. `keyserver.ubuntu.com` / `keys.openpgp.org`).
5. Tag and push: `git tag -a v1.0.0 -m "imazeo 1.0.0"` then `git push origin main` and `git push origin v1.0.0`.
6. Prefer **no local JDK**: publish from GitHub Actions
   - Repo → **Settings** → **Secrets and variables** → **Actions** → add:
     - `CENTRAL_USERNAME` — Portal token username
     - `CENTRAL_PASSWORD` — Portal token password
     - `GPG_PRIVATE_KEY` — armored private key
     - `GPG_PASSPHRASE` — key passphrase
   - Ensure `.github/workflows/publish.yml` is on `main`
   - **Actions** → **Publish Maven Central** → **Run workflow** (or push/retag `v*`)
7. Verify: https://central.sonatype.com/artifact/com.imazeo/imazeo/1.0.0  
   and Maven Central search / repo1 sync (can take minutes after Portal publish).

## After publish

```xml
<dependency>
  <groupId>com.imazeo</groupId>
  <artifactId>imazeo</artifactId>
  <version>1.0.0</version>
</dependency>
```

If the release is bad, ship a reviewed patch — do not reuse `1.0.0`.
