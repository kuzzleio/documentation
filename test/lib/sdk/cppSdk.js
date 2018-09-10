const
  {
    execute,
    getVersionPath
  } = require('../helpers/utils');

class CppSdk {
  constructor(version) {
    this.version = version;
    this.versionPath = getVersionPath('cpp', this.version);
    this.sdkCppArchive = 'kuzzlesdk-cpp.tar.gz';
    this.sdkCppDir = 'test/bin/sdk-cpp';
    this.sdkCppBucket = `https://dl.kuzzle.io/sdk/cpp/${this.versionPath}/${this.sdkCppArchive}`;
  }

  async get() {
    await execute('rm', ['-rf', this.sdkCppDir]);
    await execute('mkdir', ['-p', this.sdkCppDir]);
    await execute('curl', ['-o', this.sdkCppArchive, this.sdkCppBucket], { cwd: this.sdkCppDir });
    await execute('tar', ['-xf', this.sdkCppArchive], { cwd: this.sdkCppDir });
    await execute('rm', [this.sdkCppArchive], { cwd: this.sdkCppDir });
    await execute('cp', ['include/kuzzlesdk.h', 'include/kuzzle.h'], { cwd: this.sdkCppDir });
  }
}

module.exports = CppSdk;
