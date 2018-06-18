const fileProcess = require('../fileProcess');
const nexpect = require('nexpect');
const fs = require('fs');
const logger = require('../logger');
const config = require('../../../helpers/getConfig').get();

module.exports = class Tester {

  constructor () {
    if (new.target === Tester) {
      throw new TypeError("Cannot construct Tester instances directly");
    }
  }

  runOneTest(test, snippetPath) {
    return new Promise((resolve, reject) => {
      if (test.hooks.before) this.runBeforeScript(test.hooks.before);
      let binFile = fileProcess.injectSnippet(test, snippetPath, this.language);
      if (binFile) {
        if (!this.isTodo(snippetPath) && !this.isWontdo(snippetPath)) {
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
      } else {
        let err = {
          code : 'MISSING_SNIPPET',
          expect: test.expect,
          actual: `Missing snippet file : ${snippetPath.split('src/')[1]}.${this.language}` 
        };
        logger.reportNOk(test, err);
        reject();
        return;
      }
    });
  }

  runExpect(binFile, expected) {
    return new Promise((resolve, reject) => {
      nexpect.spawn(`${this.runCommand} ${binFile}`, { stream: 'all' })
        .wait(expected, () => {
          resolve();
          return;
        })
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
        });
    });
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
        });
    });
  }
  
  isTodo(snippetPath) {
    let 
      snippet = snippetPath + '.' + config.languages[this.language].ext,
      fileContent = fs.readFileSync(snippet, 'utf8');
    if (fileContent.match(/(\@todo)/g)) {
      
      return true;
    }  
    return false;
  }
  
  isWontdo(snippetPath) {
    let
      snippet = snippetPath + '.' + config.languages[this.language].ext,
      fileContent = fs.readFileSync(snippet, 'utf8');
    if (fileContent.match(/(\@wontdo)/g)) return true;
    return false;
  }

  runBeforeScript() {

  }
  
  runAfterScript() {

  }
};
