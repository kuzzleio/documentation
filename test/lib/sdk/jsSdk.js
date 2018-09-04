const
  {
    execute,
    getVersionPath
  } = require('../helpers/sdk');

class JsSdk {
  constructor(version) {
    this.version = version;

    this.npmPackage = `https://github.com/kuzzleio/sdk-javascript#${getVersionPath('js', this.version)}`;
  }

  async get() {
    await execute('npm', ['install', this.npmPackage]);
  }
}

module.exports = JsSdk;
