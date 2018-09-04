const
  { spawnSync } = require('child_process');

async function execute(command, args, options = {}) {
  options.encoding = 'utf8';

  const { status, output, error } = spawnSync(command, args, options);

  if (error) {
    throw error;
  }

  if (status !== 0) {
    const message = output.join("\n");

    throw new Error(message);
  }

  return status;
}

module.exports = execute;
