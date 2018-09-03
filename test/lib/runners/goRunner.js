const
  BaseRunner = require('./baseRunner'),
  nexpect = require('nexpect'),
  childProcess = require('child_process'),
  TestResult = require('../helpers/testResult');

module.exports = class GoRunner extends BaseRunner {
  constructor() {
    super();
    this.language = 'go';
    this.goProjectPath = '/go/src/github.com/kuzzleio/go-test/';
    this.runCommand = `go run ${this.goProjectPath}`;
    this.lintCommand = `golint ${this.goProjectPath}`;
  }

  runExpect(snippet) {
    const fileName = snippet.renderedSnippetPath.split('/').pop();

    childProcess.execSync(`goimports -w ${this.goProjectPath}${fileName}`);

    return new Promise((resolve, reject) => {
      nexpect
        .spawn(this.runCommand + fileName)
        .wait(snippet.expected, result => {
          if (result === snippet.expected) {
            resolve();
            return;
          }

          const res = {
            code: 'ERR_ASSERTION',
            actual: result
          };
          reject(new TestResult(res));
        })
        .run((error, output) => {
          if (error) {
            const res = {
              code: 'ERR_ASSERTION',
              actual: error.actual
            };
            reject(new TestResult(res));

            return;
          }

          if (output.includes(snippet.expected)) {
            resolve();
            return;
          }
        });
    });
  }

  lintExpect(snippet) {
    const fileName = snippet.renderedSnippetPath.split('/').pop();

    return new Promise((resolve, reject) => {
      nexpect
        .spawn(this.lintCommand + fileName, { stream: 'all' })
        .wait('')
        .run((error, output) => {
          if (error) {
            resolve();
            return;
          }

          const res = {
            code: 'LINTER ERROR',
            actual: output.join('\n')
          };
          reject(new TestResult(res));
        });
    });
  }
};
