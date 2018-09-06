const
  {
    execute,
    getVersionPath
  } = require('../helpers/utils');

class GoSdk {
  constructor(version) {
    this.version = version;

    this.repository = 'https://github.com/kuzzleio/sdk-go';
    this.sdkGoPath = '/go/src/github.com/kuzzleio/sdk-go';
  }

  async get() {
    await execute('git', ['clone', '-b', getVersionPath('go', this.version), this.repository]);
    await execute('mv', ['sdk-go', this.sdkGoPath]);
    await execute('go', ['get', './...'], { cwd: this.sdkGoPath });
  }
}

module.exports = GoSdk;
