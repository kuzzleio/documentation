const
  cout = require('./colorOutput'),
  { exec } = require('child_process'),
  fs = require('fs'),
  YAML = require('js-yaml');

const currentDir = require('path').dirname(require.main.filename);

const getRepositories = (argv) => {
  let repositoryNames = [];

  if (process.env.REPOSITORIES && process.env.REPOSITORIES.length > 0) {
    repositoryNames = process.env.REPOSITORIES.split(',');
  } else if (argv.repo) {
    repositoryNames = argv.repo;
  }

  const repositories = YAML
    .safeLoad(fs.readFileSync(`${currentDir}/repositories.yml`))
    .filter(repo => repositoryNames.length === 0 || repositoryNames.includes(repo.name));

  if (repositories.length === 0) {
    cout.error(`Unknown repository ${repositoryNames.join(',')}.`);
    process.exit(1);
  }

  return repositories;
};

const execute = (command, message) => {
  const cmd = Array.isArray(command)
    ? command.join(' ')
    : command;

  cout.notice(message);

  return new Promise(resolve => {
    exec(cmd, { maxBuffer: 1024 * 500 }, (error, stdout, stderr) => {
      if (error) {
        console.error(stdout);
        console.error(stderr);
        console.error(error);
        cout.error(cmd);

        process.exitCode = 1;

        return resolve();
      }

      cout.ok(message);
      resolve();
    })
  })
}

const cloneRepository = async (argv) => {
  let branch = 'stable';

  if (process.env.BRANCH && process.env.BRANCH.length > 0) {
    branch = process.env.BRANCH;
  } else if (argv.branch) {
    branch = argv.branch;
  }

  const promises = [];

  if (!['dev', 'stable'].includes(branch)) {
    cout.warn('--branch option can be "dev" or "stable"');
    process.exit(1);
  }

  for (const repository of getRepositories(argv)) {
    const
      message = `Cloning ${repository.name} on branch ${repository[branch]}`,
      command = [
        'git clone',
        '--depth 10',
        '--single-branch',
        `--branch ${repository[branch]}`,
        repository.url,
        `${currentDir}/${repository.destination}`
      ];

    promises.push(execute(command, message));
  }

  await Promise.all(promises);
};

const commandRepository = async (cmd, argv) => {
  const promises = [];

  for (const repository of getRepositories(argv)) {
    const
      message = `Executing command "${cmd}" for ${repository.name}`,
      command = `cd ${currentDir}/${repository.destination} && ${cmd}`;

    const promise = execute(command, message);

    if (argv.async) {
      promises.push(promise);
    } else {
      await promise;
    }
  }

  await Promise.all(promises);
};

const updateRepository = async (argv) => {
  await commandRepository('git pull', argv);
};


const removeRepository = async (argv) => {
  const promises = [];

  for (const repository of getRepositories(argv)) {
    const
      message = `Remove repository ${repository.name}`,
      command = `rm -rf ${currentDir}/${repository.destination}`;

    promises.push(execute(command, message));
  }

  await Promise.all(promises);
}



const argv = require('yargs').argv;

switch (process.argv[2]) {
  case 'run':
    const command = process.argv[3];

    commandRepository(command, argv)
    break;

  case 'clone':
    cloneRepository(argv);
    break;

  case 'update':
    updateRepository(argv);
    break;

  case 'remove':
    removeRepository(argv);
    break;

  default:
    cout.error(`Invalid command "${process.argv[2]}". Use "clone", "run", "update" or "remove".`);
    process.exit(1);
    break;
}
