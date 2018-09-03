const
  execute = require('../helpers/execute');

class JsSdk {
  constructor(version) {
    this.version = version;

    this.npmPackage = `https://github.com/kuzzleio/sdk-javascript#${this.version}`
  }

  async get() {
    await execute('npm', ['install', this.npmPackage]);
  }
}

module.exports = JsSdk;
