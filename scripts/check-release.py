"""Validate the SDK's independent SemVer, version constants and release metadata."""
import os
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
pom = (ROOT / "pom.xml").read_text()
version_match = re.search(
    r"<artifactId>imazeo</artifactId>\s*<version>([^<]+)</version>",
    pom,
    re.MULTILINE,
)
assert version_match, "Could not find project version after artifactId imazeo"
version = version_match.group(1)
constant = re.search(
    r'SDK_VERSION\s*=\s*"([^"]+)"',
    (ROOT / "src/main/java/com/imazeo/Version.java").read_text(),
).group(1)
assert re.fullmatch(
    r"(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(?:-[0-9A-Za-z.-]+)?", version
), "Invalid SemVer"
assert version == constant, "Package metadata and SDK User-Agent version disagree"
if os.environ.get("GITHUB_REF_TYPE") == "tag":
    assert os.environ.get("GITHUB_REF_NAME") == "v" + version, "Tag and SDK version disagree"
for required in ("LICENSE", "README.md", "GUIDE.md", "CHANGELOG.md"):
    assert (ROOT / required).is_file(), "Missing package document: " + required
assert "MIT License" in (ROOT / "LICENSE").read_text(), "Review SDK licensing"
assert "<groupId>com.imazeo</groupId>" in pom, "groupId must be com.imazeo"
assert "https://github.com/Imazeohq/imazeo-java" in pom, "SCM URL must point at Imazeohq/imazeo-java"
print("SDK release metadata valid:", version)
