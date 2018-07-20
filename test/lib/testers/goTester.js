const
  Tester = require('./tester'),
  nexpect = require('nexpect'),
  childProcess = require('child_process');

module.exports = class GoTester extends Tester {
  constructor() {
    super();
    this.language = 'go';
    this.goProjectPath = '/go/src/github.com/kuzzleio/go-test/';
    this.runCommand = `go run ${this.goProjectPath}`;
    this.lintCommand = `golint ${this.goProjectPath}`;
  }

  runExpect(generatedFilePath, expected) {
    const fileName = generatedFilePath.split('/').pop();

    childProcess.execSync(`goimports -w ${this.goProjectPath}${fileName}`);

    return new Promise((resolve, reject) => {
      nexpect
        .spawn(this.runCommand + fileName)
        .wait(expected, result => {
          if (result === expected) {
            resolve();
            return;
          }

          const err = {
            code: 'ERR_ASSERTION',
            actual: result
          };
          reject(err);
        })
        .run((error, output) => {
          if (error) {
            reject(error);
            return;
          }

          if (output.includes(expected)) {
            resolve();
            return;
          }

          const err = {
            code: 'ERR_ASSERTION',
            actual: output[0]
          };
          reject(err);
          return;
        });
    });
  }

  lintExpect(generatedFilePath) {
    const fileName = generatedFilePath.split('/').pop();

    return new Promise((resolve, reject) => {
      nexpect
        .spawn(this.lintCommand + fileName, { stream: 'stderr' })
        .wait('')
        .run((error, output) => {
          if (error) {
            resolve();
            return;
          }

          const err = {
            code: 'LINTER ERROR',
            actual: output.join('\n')
          };
          reject(err);
        });
    });
  }

};
