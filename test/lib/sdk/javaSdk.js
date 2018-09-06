const
  {
    execute,
    getVersionPath
  } = require('../helpers/utils');

class CppSdk {
  constructor(version) {
    this.version = version;
    this.versionPath = getVersionPath('cpp', this.version);
    this.sdkJavaJar = 'kuzzlesdk-java.jar';
    this.sdkJavaDir = '/app/test/bin/';
    this.sdkJavaBucket = `https://dl.kuzzle.io/sdk/java/${this.versionPath}/${this.sdkJavaJar}`;
  }

  async get() {
    await execute('rm', ['-rf', this.sdkJavaJar], { cwd: this.sdkJavaDir });
    await execute('curl', ['-o', this.sdkJavaJar, this.sdkJavaBucket], { cwd: this.sdkJavaDir });
  }
}

module.exports = CppSdk;
