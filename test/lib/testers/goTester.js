const Tester = require('./tester');
const path = require('path');
const nexpect = require('nexpect');

module.exports = class GoTester extends Tester {
  constructor() {
    super();
    let binPath = path.join(__dirname, '../../bin')
    this.language = 'go';
    this.runCommand = 'ln-s && go run';
    this.lintCommand = `golint`;
    this.indentation = 'space'; //tab or space
  }
};