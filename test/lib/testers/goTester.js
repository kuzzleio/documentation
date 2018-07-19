const Tester = require('./tester');
const path = require('path');
const nexpect = require('nexpect');
const childProcess = require('child_process');

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
          if (result == expected) {
            resolve();
            return;
          }
          const err = {
            code: 'ERR_ASSERTION',
            actual: result
          }
          reject(err);
          return;
        })
        .run((err, outpout) => {
          if (err) {
            reject(err);
            return;
          }
          if (outpout.includes(expected)) {
            resolve();
            return;
          }
          err = {
            code: 'ERR_ASSERTION',
            actual: outpout[0]
          }
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
        .run((err, output, exit) => {
          if (err) {
            resolve();
          } else {
            const err = {
              code: 'LINTER ERROR',
              actual: output.join('\n')
            }
            reject(err);
          }
        });
    });
  }

};
