#!/usr/bin/env node
/** Keep pom.xml version and Version.SDK_VERSION in sync after release-it bumps package.json. */
import { readFileSync, writeFileSync } from 'node:fs';
import { spawnSync } from 'node:child_process';
import { dirname, join } from 'node:path';
import { fileURLToPath } from 'node:url';

const root = join(dirname(fileURLToPath(import.meta.url)), '..');
const version = JSON.parse(readFileSync(join(root, 'package.json'), 'utf8')).version;

const pomPath = join(root, 'pom.xml');
const pom = readFileSync(pomPath, 'utf8');
const nextPom = pom.replace(
  /(<artifactId>imazeo<\/artifactId>\s*<version>)[^<]+(<\/version>)/,
  `$1${version}$2`,
);
if (!nextPom.includes(`<artifactId>imazeo</artifactId>\n  <version>${version}</version>`) &&
    !new RegExp(`<artifactId>imazeo</artifactId>\\s*<version>${version.replace(/\./g, '\\.')}</version>`).test(nextPom)) {
  console.error('sync-sdk-version: failed to update pom.xml');
  process.exit(1);
}
writeFileSync(pomPath, nextPom, 'utf8');

const versionJavaPath = join(root, 'src', 'main', 'java', 'com', 'imazeo', 'Version.java');
const versionJava = readFileSync(versionJavaPath, 'utf8');
const nextVersionJava = versionJava.replace(
  /SDK_VERSION\s*=\s*"[^"]+"/,
  `SDK_VERSION = "${version}"`,
);
if (!nextVersionJava.includes(`SDK_VERSION = "${version}"`)) {
  console.error('sync-sdk-version: failed to update Version.java');
  process.exit(1);
}
writeFileSync(versionJavaPath, nextVersionJava, 'utf8');

const add = spawnSync(
  'git',
  ['add', 'pom.xml', 'src/main/java/com/imazeo/Version.java', 'package.json'],
  {
    cwd: root,
    stdio: 'inherit',
    shell: false,
  },
);
if (add.status !== 0) process.exit(add.status ?? 1);
console.log(`sync-sdk-version: ${version}`);
