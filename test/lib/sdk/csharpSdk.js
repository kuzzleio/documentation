const
  {
    execute,
    getVersionPath
  } = require('../helpers/utils'),
  fs = require('fs');

class CsharpSdk {
  constructor(version) {
    this.name = 'csharp';
    this.version = version;
    this.versionPath = getVersionPath(this);
    this.sdkDir = 'test/bin/csharp-sdk';
    this.sdkCsharpRepo = `https://github.com/kuzzleio/sdk-csharp`;
  }

  async get() {
    await execute('rm', ['-rf', this.sdkDir]);
    await execute('git', ['clone', '--single-branch', '--branch', this.versionPath, this.sdkCsharpRepo, this.sdkDir]);
    await execute('dotnet', ['build', 'test/bin/csharp-sdk']);
    await execute('mv', ['test/bin/csharp-sdk/Kuzzle/bin/Debug/netstandard2.0/Kuzzle.dll', 'test/bin/Kuzzle.dll']);
  }

  exists() {
    return fs.existsSync(this.sdkDir);
  }
}

module.exports = CsharpSdk;
