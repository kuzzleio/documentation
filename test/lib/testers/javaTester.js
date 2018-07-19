const
  Tester = require('./tester'),
  path = require('path'),
  nexpect = require('nexpect');

module.exports = class JavaTester extends Tester {
  constructor() {
    super();
    this.language = 'java';
    this.runCommand = 'java -cp ./test/bin/sdk-java.jar:./test/bin';
    this.lintCommand = 'javac -cp test/bin/sdk-java.jar test/bin/';
  }

  runExpect (generatedFilePath, expected) {
    const generatedFilename = path.basename(generatedFilePath, '.html');

    return new Promise((resolve, reject) => {
      nexpect
        .spawn(`${this.runCommand} ${generatedFilename}`, { stream: 'all' })
        .wait(expected, result => {
          if (result === expected) {
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
        .run((err, output) => {
          if (err) {
            reject(err);
            return;
          }

          if (output.includes(expected)) {
            resolve();
            return;
          }

          const error = {
            code: 'ERR_ASSERTION',
            actual: output[0]
          }
          reject(error);
          return;
        });
    });
  }

  lintExpect (generatedFilePath) {
    const generatedFilename = path.basename(generatedFilePath);

    return new Promise((resolve, reject) => {
      nexpect
        .spawn(`${this.lintCommand}${generatedFilename}`, { stream: 'all' })
        .wait('')
        .run((err, output, exit) => {
          if (err) {
            resolve();
            return;
          }

          const error = {
            code: 'LINTER_ERROR',
            actual: output.join("\n")
          }

          reject(error);
        });
    });
  }
};
