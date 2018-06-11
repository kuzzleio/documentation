const fileProcess = require('../fileProcess');
const nexpect = require('nexpect');
const fsSync = require('fs-sync');
const logger = require('../logger');

module.exports = class Tester {

  constructor () {

  }

  runOneTest(test, snippetPath) {
    if (test.hooks.before) this.runBeforeScript(test.hooks.before);

    let binFile = fileProcess.injectSnippet(test, snippetPath, this.language);

    if (binFile && !this.isTodo(binFile) && !this.isWontdo(binFile)) {
      let testSuccess = true;
      this.lintExpect(binFile)
        .catch((err) => {
          testSuccess = false;
          fileProcess.saveOnFail(binFile, test.name, this.language);
          logger.reportLintNOk(test, err);
        })
        .then(() => {
          if (testSuccess) {
            this.runExpect(binFile, test.expect)
              .catch((err) => {
                testSuccess = false;
                fileProcess.saveOnFail(binFile, test.name, this.language);
                logger.reportNOk(test, err);
              })
              .then(() => {
                if (testSuccess) {
                  logger.reportOk(test);
                }
              });
          }
        });
    }
  }

  runExpect(binFile, expected) {
    return new Promise((resolve, reject) => {
      nexpect.spawn(`${this.runCommand} ${binFile}`)
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
    return new Promise((resolve, reject) => {
      nexpect.spawn(`${this.lintCommand} ${binFile}`)
        .wait(this.expectedLintSuccess)
        .run((err) => {
          if (err) {
            reject(err);
          } else {
            resolve();
          }
        })
    })
  }
  
  isTodo(binfile) {
    let fileContent = fsSync.read(binfile);
    if (fileContent.match(/(\@todo)/g)) return true;
    return false;
  }
  
  isWontdo(binfile) {
    let fileContent = fsSync.read(binfile);
    if (fileContent.match(/(\@wontdo)/g)) return true;
    return false;
  }

  runBeforeScript() {

  }
  
  runAfterScript() {

  }
};
