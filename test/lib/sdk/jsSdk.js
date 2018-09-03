const
  { spawnSync } = require('child_process');

class JsSdk {
  constructor(version) {
    this.version = version;
    this.npmPackage = `https://github.com/kuzzleio/sdk-javascript#${this.version}`
  }

  async get() {
    const { status, stdout } = spawnSync('npm', ['install', this.npmPackage]);

    if (status !== 0) {
      throw new Error(`Can't download the javascript SDK ${this.version}\n${stdout.toString()}`);
    }
  }
}

module.exports = JsSdk;
