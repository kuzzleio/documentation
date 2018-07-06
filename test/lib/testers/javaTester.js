const Tester = require('./tester');
const nexpect = require('nexpect');

module.exports = class JavaTester extends Tester {
  constructor() {
    super();
    this.language = 'java';
    this.runCommand = 'java -cp ./test/bin/sdk-java.jar:./test/bin';
    this.lintCommand = 'javac -cp test/bin/sdk-java.jar test/bin/';
  }
  
  runExpect(binFile, expected) {
    binFile = binFile.split('/').pop().split('.')[0];
    return new Promise((resolve, reject) => {
      nexpect.spawn(`${this.runCommand} ${binFile}`, { stream: 'all' })
        .wait(expected, result => {
          if (result == expected) {
            resolve();
            return;
          }
          let err = {
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

  lintExpect(binFile) {
    binFile = binFile.split('/').pop();
    return new Promise((resolve, reject) => {
      nexpect.spawn(`${this.lintCommand}${binFile}`, { stream: 'all' })
        .wait('')
        .run((err, outpout, exit) => {
          if (err) {
            resolve();
          } else {
            let err = {
              code: 'LINTER_ERROR',
              actual: outpout.join()
            }
            reject(err);
          }
        });
    });
  }
};