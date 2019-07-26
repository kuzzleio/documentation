const
  { exec } = require('child_process'),
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

let docDir = `${currentDir}/../..`;
// This variable is set if we are inside a sub-site build
if (process.env.DOC_DIR) {
  docDir = `${currentDir}/../../../../${process.env.DOC_DIR}`;
}

let repoDir = `${currentDir}/../../..`;
// This variable is set if we are inside a sub-site build
if (process.env.DOC_DIR) {
  repoDir = `${currentDir}/../../../../${process.env.DOC_DIR}`;
}

console.log(`Doc dir ${docDir}`);
console.log(`Repo dir ${repoDir}`);

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
  const repository = require(`${repoDir}/package.json`).repository.url;

  const content = getVersionFileContent(site, repository, commit.replace('\n', ''));

  fs.writeFileSync(versionFilePath, content);
};


module.exports = {
  name: 'generate-version-file',
  async ready () {
    await generateVersionFile()
  }
};