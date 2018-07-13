const Tester = require('./tester');
const path = require('path');
const nexpect = require('nexpect');
const childProcess = require('child_process');

module.exports = class CppTester extends Tester {
  constructor() {
    super();
    this.language = 'cpp';
    this.runCommand = 'g++';
    this.lintCommand = 'cpplint --filter=-legal/copyright' ;
  }

  runExpect(binFile, expected) {
    const fullPath = binFile.split('.')[0];
    // console.log(`${this.runCommand} -o ${fullPath} ${binFile} && ${fullPath}`)
    childProcess.execSync(`${this.runCommand} -o ${fullPath} ${binFile}`);
    return new Promise((resolve, reject) => {
      nexpect.spawn(fullPath, { stream: 'all' })
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
            actual: outpout
          }
          reject(err);
          return;
        });
    });
  }
  
  lintExpect(binFile) {
    const expected = `Done processing ${binFile}`
    return new Promise((resolve, reject) => {
      nexpect.spawn(`${this.lintCommand} ${binFile}`, { stream: 'all' })
        .wait(expected)
        .run((err, outpout, exit) => {
          if (err) {
            reject(err);
          } else {
            resolve();
          }
        });
    });
  }
};