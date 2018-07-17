const fileHelper = require('../helpers/file');
const Tester = require('./tester');
const path = require('path');
const nexpect = require('nexpect');
const childProcess = require('child_process');

module.exports = class CppTester extends Tester {
  constructor() {
    super();
    this.language = 'cpp';
    this.compileCommand = 'g++ -Isdk-cpp/ -Lsdk-cpp/ -lkuzzlesdk-cpp -lpthread';
    this.runCommand = '';
    this.lintCommand = 'cpplint --filter=-legal/copyright';
    this.executablePath = '';
  }

  runExpect(generatedFilePath, expected) {
    process.env.LD_LIBRARY_PATH = './sdk-cpp';
    this.executablePath = generatedFilePath.split('.')[0];

    childProcess.execSync(`${this.compileCommand} -o ${this.executablePath} ${generatedFilePath}`);
    return new Promise((resolve, reject) => {
      nexpect.spawn(`${this.executablePath}`, { stream: 'all' })
        .wait(expected, result => {
          if (result === expected) {
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

  lintExpect(generatedFilePath) {
    const expected = `Done processing ${generatedFilePath}`
    return new Promise((resolve, reject) => {
      nexpect.spawn(`${this.lintCommand} ${generatedFilePath}`, { stream: 'all' })
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

  clean(generatedFilePath) {
    fileHelper.remove(generatedFilePath)
    fileHelper.remove(this.executablePath)
  }
};
