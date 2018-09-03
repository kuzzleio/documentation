const
  { spawnSync } = require('child_process');

async function execute(command, args, options) {
  const { status, output, error } = spawnSync(command, args, {encoding: 'utf8', ...options});

  if (error) {
    throw error;
  }

  if (status !== 0) {
    const message = output.join("\n");

    throw new Error(message);
  }
}

module.exports = execute;
