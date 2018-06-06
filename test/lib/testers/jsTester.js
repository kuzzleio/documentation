const Tester = require('./tester');
const path = require('path');
const nexpect = require('nexpect');

module.exports = class JsTester extends Tester{
  constructor() {
    super();
    let lintConfig = path.join(__dirname, '../../linter-config/eslint.json');
    this.language = 'js';
    this.runCommand = 'node';
    this.lintCommand = `eslint -c ${lintConfig}`;
    this.indentation = 'space'; //tab or space
  }
  
  /**
   * Override method because eslint 
   * doesn't return value on success 
   */
  lintExpect(binFile) {
    return new Promise((resolve, reject) => {
      nexpect.spawn(`${this.lintCommand} ${binFile}`)
        .wait('')
        .run((err, outpout, exit) => {
          if (err) {
            resolve();
          } else {
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