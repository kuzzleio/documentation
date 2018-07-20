const
  Tester = require('./tester'),
  path = require('path'),
  fileHelper = require('../helpers/file'),
  nexpect = require('nexpect');

module.exports = class JavaTester extends Tester {
  constructor() {
    super();
    this.language = 'java';
    this.runCommand = 'java -cp ./test/bin/sdk-java.jar:./test/bin';
    this.lintCommand = 'javac -cp test/bin/sdk-java.jar test/bin/';
  }

  runExpect (generatedFilePath, expected) {
    const generatedFilename = path.basename(generatedFilePath, '.java');

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
        });
    });
  }

  lintExpect (generatedFilePath) {
    const generatedFilename = path.basename(generatedFilePath);

    return new Promise((resolve, reject) => {
      nexpect
        .spawn(`${this.lintCommand}${generatedFilename}`, { stream: 'all' })
        .wait('')
        .run((error, output) => {
          if (error) {
            resolve();
            return;
          }

          const err = {
            code: 'LINTER_ERROR',
            actual: output.join('\n')
          };
          reject(err);
        });
    });
  }

  clean(generatedFilePath) {
    fileHelper.remove(generatedFilePath);
    fileHelper.remove(generatedFilePath.replace('.java', '.class'));
  }
};
