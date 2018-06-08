const Tester = require('./tester');
const path = require('path');
const nexpect = require('nexpect');

module.exports = class GoTester extends Tester {
  constructor() {
    super();
    let binPath = path.join(__dirname, '../../bin')
    this.language = 'go';
    this.runCommand = 'go run /go/src/github.com/kuzzleio/go-test/bin.go';
    this.lintCommand = `golint /go/src/github.com/kuzzleio/go-test/bin.go`;
    this.indentation = 'space'; //tab or space
  }

  runExpect(binFile, expected) {
    return new Promise((resolve, reject) => {
      nexpect.spawn(this.runCommand)
        .wait(expected)
        .run((err) => {
          if (err) {
            reject(err);
          } else {
            resolve();
          }
        })
    })
  }
  
  lintExpect(binFile) {
    console.log('lint')
    return new Promise((resolve, reject) => {
      nexpect.spawn(this.lintCommand)
        .wait('')
        .run((err, outpout, exit) => {
          if (err) {
            resolve();
          } else {
            console.log(outpout)
            let err = {
              code: 'ESLINT',
              actual: outpout.join()
            }
            reject(err);
          }
        })
    })
  }

};