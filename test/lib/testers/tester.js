const fileProcess = require('../fileProcess');
const nexpect = require('nexpect');
const logger = require('../logger')

module.exports = class Tester {
  
  constructor () {
    
  }
  
  runOneTest(test, snippetPath) {
    if (test.hooks.before) this.runBeforeScript(test.hooks.before);

    let binFile = fileProcess.injectSnippet(test, snippetPath, this.language);

    if (binFile) {
      let testSuccess = true;
      this.lintExpect(binFile)
        .catch((err) => {
          testSuccess = false;
          fileProcess.saveOnFail(binFile, test.name, this.language);
          console.log('1')
          logger.reportLintNOk(test, err);
        })
        .then(() => {
          if (testSuccess) {
            this.runExpect(binFile, test.expect)
              .catch((err) => {
                console.log('2')
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
    } else {
      logger.reportNOk(test, false);
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
      resolve();
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

  runBeforeScript() {

  }

  runAfterScript() {

  }
};