const
  cout = require('./colorOutput'),
  { exec } = require('child_process'),
  execa = require('execa'),
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
      console.error(stdout);
      console.error(stderr);
      console.error(error);
      if (error) {
        cout.error(cmd);

        process.exitCode = 1;

        return resolve();
      }

      cout.ok(message);
      resolve();
    });
  });
};

const cloneRepository = async (argv) => {
  let branch = 'stable';

  if (process.env.BRANCH && process.env.BRANCH.length > 0) {
    branch = process.env.BRANCH;
  } else if (argv.branch) {
    branch = argv.branch;
  } else if (process.env.TRAVIS_BRANCH && process.env.TRAVIS_BRANCH.length > 0) {
    branch = process.env.TRAVIS_BRANCH.match(/^master|[0-9]+-stable$/)
      ? 'stable'
      : 'dev';
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

const prepareRepository = async (argv) => {
  for (const repository of getRepositories(argv)) {
    console.log(`Preparing repository ${repository.name}...`);
    await execa('rm', ['-f', `${currentDir}/${repository.destination}/${repository.local_path}/.vuepress`]);
    /**
     * @aschen asks "Why do we delete package.json here?"
     * 
     * @xbilll82 answers.
     * The aim here is to build a sub-repo (e.g. core-2) by linking the 
     * .vuepress files inside it and passing it to the vuepress binary 
     * installed in the main repo (i.e. this one), so that we don't have 
     * to re-clone the main repo into the sub-repos that were cloned into 
     * the main repo (let's avoit Git inception).
     * So, we take the .vuepress files from src/.vuepress and link them to 
     * .repos/core-2/doc/2/, and then call $(npm bin)/vuepress build 
     * .repos/core-2/doc/2/, right? The weird thing is that vuepress 
     * complains about not finding .repos/core-2/doc/package.json and I 
     * frankly don't undertand why it does need it at that precise path.
     * The workaround I found is to link package.json to .repos/core-2/doc/package.json 
     * and it's working all right. For this reason, before linking it, I rm -f a 
     * hypothetic .repos/core-2/doc/package.json file that might have been 
     * put there before, so that the ln -s doesn't fail.
     */
    await execa('rm', ['-f', `${currentDir}/${repository.destination}/${repository.local_path}/../package.json`]);
    await execa('ln', ['-s', '../../../../src/.vuepress', `${currentDir}/${repository.destination}/${repository.local_path}/`])
    await execa('ln', ['-s', `../../../package.json`, `${currentDir}/${repository.destination}/${repository.local_path}/../`]);
    console.log('done!')
  }
}

const buildRepository = async (argv) => {
  for (const repository of getRepositories(argv)) {
    console.log(`Building repository ${repository.name}...`);
    await execa('vuepress', ['build', `${currentDir}/${repository.destination}/${repository.local_path}`], {
      env: {
        REPO_NAME: repository.name,
        SITE_BASE: repository.base_url
      }
    }).stdout.pipe(process.stdout);
  }
}

const commandRepository = async (cmd, argv) => {
  const promises = [];

  for (const repository of getRepositories(argv)) {
    const
      message = `Executing command "${cmd}" for ${repository.name}`,
      command = `cd ${currentDir}/${repository.destination} && REPO_NAME=${repository.name} ${cmd}`;

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

const devServer = async (argv) => {
  for (const repository of getRepositories(argv)) {
    const
      message = `Link build from repository ${repository.name}`,
      linkTarget = `${currentDir}/${repository.destination}/doc/framework/src/.vuepress/dist/`,
      linkName = `${currentDir}/../src/.vuepress/dist${repository.base_url}`,
      command = `rm -rf ${linkName} && ln -s ${linkTarget} ${linkName}`;

    await execute(`mkdir -p ${currentDir}/../src/.vuepress/dist${repository.base_url}`, 'Creating subfolders');
    await execute(command, message);
  }

  await execute(
    `cd ${currentDir}/../src/.vuepress/dist && python -m SimpleHTTPServer 8000`,
    'Run local dev server on http://localhost:8000');
};

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

  case 'prepare':
    prepareRepository(argv);
    break

  case 'build':
    buildRepository(argv);
    break

  case 'dev':
    commandRepository('npm run doc-dev', argv);
    break

  case 'devServer':
    devServer(argv);
    break;

  default:
    cout.error(`Invalid command "${process.argv[2]}". Use "clone", "run", "update" or "remove".`);
    process.exit(1);
    break;
}
