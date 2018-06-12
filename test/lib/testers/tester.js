const fileProcess = require('../fileProcess');
const nexpect = require('nexpect');
const fsSync = require('fs-sync');
const fs = require('fs');
const logger = require('../logger');
const config = require('../../../helpers/getConfig').get();

module.exports = class Tester {

  constructor () {

  }

  runOneTest(test, snippetPath) {
    return new Promise((resolve, reject) => {
      if (test.hooks.before) this.runBeforeScript(test.hooks.before);
      if (!this.isTodo(snippetPath) && !this.isWontdo(snippetPath)) {
        let binFile = fileProcess.injectSnippet(test, snippetPath, this.language);

        if (binFile) {
          let testSuccess = true;
          this.lintExpect(binFile)
            .catch((err) => {
              testSuccess = false;
              fileProcess.saveOnFail(binFile, test.name, this.language);
              logger.reportLintNOk(test, err);
              reject();
              return;
            })
            .then(() => {
              if (testSuccess) {
                this.runExpect(binFile, test.expect)
                  .catch((err) => {
                    testSuccess = false;
                    fileProcess.saveOnFail(binFile, test.name, this.language);
                    logger.reportNOk(test, err);
                    reject();
                    return;
                  })
                  .then(() => {
                    if (testSuccess) {
                      logger.reportOk(test);
                      resolve();
                      return;
                    }
                  });
              }
            });
          
        }
      }
    });
  }

  runExpect(binFile, expected) {
    return new Promise((resolve, reject) => {
      nexpect.spawn(`${this.runCommand} ${binFile}`)
        .wait(expected)
        .run((err, outpout) => {
          if (err) {
            reject(err);
            return;
          }
          if(outpout.includes(expected)) {
            resolve();
            return;
          }
          err = {
            code: 'ERR_ASSERTION',
            actual: outpout[0]
          }
          reject(err);
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
  
  isTodo(snippetPath) {
    let 
      snippet = snippetPath + '.' + config.languages[this.language].ext,
      fileContent = fsSync.read(snippet);
    if (fileContent.match(/(\@todo)/g)) return true;
    return false;
  }
  
  isWontdo(snippetPath) {
    let
      snippet = snippetPath + '.' + config.languages[this.language].ext,
      fileContent = fsSync.read(snippet);
    if (fileContent.match(/(\@wontdo)/g)) return true;
    return false;
  }

  runBeforeScript() {

  }
  
  runAfterScript() {

  }
};
