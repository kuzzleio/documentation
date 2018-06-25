const Tester = require('./tester');
const path = require('path');
const nexpect = require('nexpect');
const childProcess = require('child_process');

module.exports = class GoTester extends Tester {
  constructor() {
    super();
    let binPath = path.join(__dirname, '../../bin')
    this.language = 'go';
    this.goPath = '/go/src/github.com/kuzzleio/go-test/'
    this.runCommand = 'go run /go/src/github.com/kuzzleio/go-test/';
    this.lintCommand = `golint /go/src/github.com/kuzzleio/go-test/`;
    this.indentation = 'space'; //tab or space
  }

  runExpect(binFile, expected) {
    let fileName = binFile.split('/').pop();
    childProcess.execSync(`goimports -w ${this.goPath}${fileName}`);
    // console.log(`goimports ${this.goPath}${fileName} > ${this.goPath}${fileName}`);
    return new Promise((resolve, reject) => {
      nexpect.spawn(this.runCommand + fileName)
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
    let fileName = binFile.split('/').pop();
    return new Promise((resolve, reject) => {
      nexpect.spawn(this.lintCommand + fileName, { stream: 'stderr' })
        .wait('')
        .run((err, outpout, exit) => {
          if (err) {
            resolve();
          } else {
            let err = {
              code: 'LINTER ERROR',
              actual: outpout.join()
            }
            reject(err);
          }
        });
    });
  }

};