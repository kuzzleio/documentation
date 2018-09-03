const
  { spawnSync } = require('child_process');

class Sdk {
  constructor(version) {
    this.version = version;
  }

  async execute(command, args) {
    const { status, stdout } = spawnSync(command, args);

    if (status !== 0) {
      throw new Error(`Error when installing the SDK\n${stdout.toString()}`);
    }
  }
}

module.exports = Sdk;
