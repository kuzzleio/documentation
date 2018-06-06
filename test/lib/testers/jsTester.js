const Tester = require('./tester');
const path = require('path');
const nexpect = require('nexpect');

module.exports = class JsTester extends Tester{
  constructor() {
    super();
    let lintConfig = path.join(__dirname, '../../linters/eslint.json');
    this.language = 'js';
    this.runCommand = 'node';
    this.lintCommand = `eslint -c ${lintConfig}`;
    this.indentation = 'space'; //tab or space
  }
  
  /**
   * Override method because eslint 
   * return nothing on success
   *  
   * - if eslint success : wait() return error because nothing append and process exit : test passed
   * - if eslint fail: we get something in stdOut, so wait() continue waiting, so no error : test failed
   * (O.M.G.W.T.F)
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