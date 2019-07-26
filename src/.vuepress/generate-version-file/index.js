const
  { exec } = require('child_process'),
  path = require('path'),
  fs = require('fs');

const execute = command => {
  const cmd = Array.isArray(command)
    ? command.join(' ')
    : command;

  return new Promise(resolve => {
    exec(cmd, { maxBuffer: 1024 * 500 }, (error, stdout, stderr) => {
      if (error) {
        console.error(stdout);
        console.error(stderr);
        console.error(error);

        process.exitCode = 1;

        return reject();
      }

      resolve(stdout);
    });
  });
};

const currentDir = __dirname;

let docDir = path.resolve(`${currentDir}/../..`);
// This variable is set if we are inside a sub-site build
if (process.env.DOC_DIR) {
  console.log('sub dir')
  docDir = path.resolve(`${currentDir}/../../../../${process.env.DOC_DIR}`);
}

let repoDir = path.resolve(`${currentDir}/../../..`);
// This variable is set if we are inside a sub-site build
if (process.env.DOC_DIR) {
  repoDir = path.resolve(`${currentDir}/../../../../..`);
}

const versionFilePath = `${docDir}/version.md`;
const getVersionFileContent = (site, repository, commit) => `---
code: false
type: page
order: 0
title: Kuzzle Documentation Version
---


<Version site="${site}" repository="${repository}" commit="${commit}" />
`;

const generateVersionFile = async () => {
  const site = process.env.SITE_BASE || '/';
  const commit = await execute(`cd ${repoDir} && git rev-parse HEAD`);
  const package = require(`${repoDir}/package.json`);
  const repository = package.repository ? package.repository.url : package.name;

  const content = getVersionFileContent(site, repository, commit.replace('\n', ''));

  console.log(`Write version file at ${versionFilePath}`);

  fs.writeFileSync(versionFilePath, content);
};


module.exports = {
  name: 'generate-version-file',
  async ready () {
    await generateVersionFile()
  }
};