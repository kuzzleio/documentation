const fileHelper = require('../helpers/file');
const nexpect = require('nexpect');
const fs = require('fs');
const childProcess = require('child_process');
const logger = require('../helpers/logger');
const config = require('../../../getConfig').get();

module.exports = class Tester {

  constructor () {
    if (new.target === Tester) {
      throw new TypeError("Cannot construct Tester instances directly");
    }
  }
  
  runOneTest(test, snippetPath) {
    return new Promise(async (resolve, reject) => {
      if (test.hooks.before) this.runHookCommand(test.hooks.before);
      
      if (this.isTodo(snippetPath, test) || this.isWontdo(snippetPath, test)) {
        if (test.hooks.after) this.runHookCommand(test.hooks.after);
        resolve();
        return;
      }
      
      let binFile = fileHelper.injectSnippet(test, snippetPath, this.language);
      if (!binFile) {
        let err = {
          code: 'MISSING_SNIPPET',
          expect: test.expect,
          actual: `Missing snippet file : ${snippetPath.split('src/')[1]}.${this.language}`
        };
        logger.reportNOk(test, err, this.language);
        reject();
        return;
      }
      
      try {
        await this.lintExpect(binFile);
        await this.runExpect(binFile, test.expect);
      } catch (err) {
        fileHelper.saveOnFail(binFile, test.name, this.language);
        // fileHelper.removeBin(binFile);
        
        
        err.file = snippetPath.split('src/')[1] + '.' + config.languages[this.language].ext;
        logger.reportNOk(test, err, this.language);
        
        if (test.hooks.after) this.runHookCommand(test.hooks.after);
        
        reject();
        return;
      }
      
      logger.reportOk(test, this.language);
      if (test.hooks.after) this.runHookCommand(test.hooks.after);
      // fileHelper.removeBin(binFile);
      resolve();
      return;
    });
  }

  runExpect(binFile, expected) {
    return new Promise((resolve, reject) => {
      nexpect.spawn(`${this.runCommand} ${binFile}`, { stream: 'all' })
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
          if(outpout.includes(expected)) {
            resolve();
            return;
          }
          err = {
            code: 'ERR_ASSERTION',
            actual: outpout[0]
          }
          reject(err);
          return;
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
  
  isTodo(snippetPath, test) {
    let 
      snippet = snippetPath + '.' + config.languages[this.language].ext,
      fileContent = fs.readFileSync(snippet, 'utf8');
      
    if (fileContent.match(/(\@todo)/g)) {
      let err = {
        code: 'TODO',
        expect: test.expect,
        actual: '',
        file: snippet
      };
      logger.reportToJson(test, err, this.language);
      return true;
    }  
    return false;
  }
  
  isWontdo(snippetPath, test) {
    let
      snippet = snippetPath + '.' + config.languages[this.language].ext,
      fileContent = fs.readFileSync(snippet, 'utf8');
    if (fileContent.match(/(\@wontdo)/g)){
      let err = {
        code: 'WONTDO',
        expect: test.expect,
        actual: '',
        file: snippet
      };
      logger.reportToJson(test, err, this.language);
      return true;
    } 
    return false;
  }

  runHookCommand(command) {
    childProcess.execSync(command)
  }
  
};
