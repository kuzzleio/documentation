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
    this.sdkCppArchive = 'kuzzlesdk-cpp-experimental-amd64.tar.gz';
    this.sdkDir = 'test/bin/sdk-cpp';
    this.sdkCppBucket = `https://dl.kuzzle.io/sdk/cpp/${this.versionPath}/${this.sdkCppArchive}`;
  }

  async get() {
    await execute('rm', ['-rf', this.sdkDir]);
    await execute('mkdir', ['-p', this.sdkDir]);
    await execute('curl', ['-o', this.sdkCppArchive, this.sdkCppBucket], { cwd: this.sdkDir });
    await execute('tar', ['-xf', this.sdkCppArchive], { cwd: this.sdkDir });
    await execute('rm', [this.sdkCppArchive], { cwd: this.sdkDir });
    await execute('cp', ['include/kuzzlesdk.h', 'include/kuzzle.h'], { cwd: this.sdkDir });
  }

  exists() {
    return fs.existsSync(this.sdkDir);
  }
}

module.exports = CppSdk;
