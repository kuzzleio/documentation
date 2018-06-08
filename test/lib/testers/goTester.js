const Tester = require('./tester');
const path = require('path');
const nexpect = require('nexpect');

module.exports = class GoTester extends Tester {
  constructor() {
    super();
    let binPath = path.join(__dirname, '../../bin')
    this.language = 'go';
    this.runCommand = 'ln -s /app/test/bin/*.go /go/src/github.com/kuzzleio/go-test/ && go run';
    this.lintCommand = `golint`;
    this.indentation = 'space'; //tab or space
  }

  runExpect(binFile, expected) {
    return new Promise((resolve, reject) => {
      nexpect.spawn(`${this.runCommand} /go/src/github.com/kuzzleio/go-test/*.go`)
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

};
