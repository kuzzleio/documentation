const
  {
    execute,
    getVersionPath
  } = require('../helpers/utils'),
  fs = require('fs');

class CppSdk {
  constructor(version) {
    this.version = version;
    this.versionPath = getVersionPath('cpp', this.version);
    this.sdkJavaJar = 'kuzzlesdk-java.jar';
    this.sdkDir = 'test/bin/sdk-java';
    this.sdkJavaBucket = `https://dl.kuzzle.io/sdk/java/${this.versionPath}/${this.sdkJavaJar}`;
  }

  async get() {
    await execute('mkdir', ['-p', this.sdkDir]);
    await execute('rm', ['-rf', this.sdkJavaJar], { cwd: this.sdkDir });
    await execute('curl', ['-o', this.sdkJavaJar, this.sdkJavaBucket], { cwd: this.sdkDir });
  }

  exists() {
    return fs.existsSync(this.sdkDir);
  }
}

module.exports = CppSdk;
