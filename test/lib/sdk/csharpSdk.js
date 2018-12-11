const
  {
    execute,
    getVersionPath
  } = require('../helpers/utils'),
  fs = require('fs');

class CsharpSdk {
  constructor(version) {
    this.name = 'csharp';
    this.ext = 'cs';
    this.version = version;
    this.versionPath = getVersionPath(this);
    this.sdkCsharpArchive = 'kuzzlesdk-csharp-experimental-amd64.tar.gz';
    this.sdkDir = 'test/bin/';
    this.archiveDir = 'kuzzle-csharp-sdk';
    this.sdkCsharpBucket = `https://dl.kuzzle.io/sdk/csharp/${this.versionPath}/${this.sdkCsharpArchive}`;
  }

  async get() {
    await execute('rm', [`${this.archiveDir}/kuzzlesdk-0.0.1.dll`], { cwd: this.sdkDir });
    await execute('rm', [`${this.archiveDir}/libkuzzle-wrapper-csharp.dll`], { cwd: this.sdkDir });
    await execute('rm', [`${this.archiveDir}/libkuzzlesdk.so`], { cwd: this.sdkDir });
    await execute('curl', ['-o', this.sdkCsharpArchive, this.sdkCsharpBucket], { cwd: this.sdkDir });
    await execute('tar', ['-xf', this.sdkCsharpArchive], { cwd: this.sdkDir });
    await execute('mv', [`${this.archiveDir}/kuzzlesdk-0.0.1.dll`, '.'], { cwd: this.sdkDir });
    await execute('mv', [`${this.archiveDir}/libkuzzle-wrapper-csharp.dll`, '.'], { cwd: this.sdkDir });
    await execute('mv', [`${this.archiveDir}/libkuzzlesdk.so`, '.'], { cwd: this.sdkDir });
    await execute('rm', ['-r', this.archiveDir, this.sdkCsharpArchive], { cwd: this.sdkDir });
  }

  exists() {
    return fs.existsSync(`${this.sdkDir}/kuzzlesdk-0.0.1.dll`);
  }
}

module.exports = CsharpSdk;
