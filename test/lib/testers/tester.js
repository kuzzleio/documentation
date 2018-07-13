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
      if (this.isTodo(snippetPath, test) || this.isWontdo(snippetPath, test)) {
        resolve();
        return;
      }

      const binFile = fileHelper.injectSnippet(test, snippetPath, this.language);

      if (! binFile) {
        const err = {
          code: 'MISSING_SNIPPET',
          expect: test.expect,
          actual: `Missing snippet file : ${snippetPath.split('src/')[1]}.${this.language}`
        };
        logger.reportNOk(test, err, this.language);
        reject();
        return;
      }

      try {
        if (test.hooks.before)
          this.runHookCommand(test.hooks.before)

        await this.lintExpect(binFile);
        await this.runExpect(binFile, test.expect);

        logger.reportOk(test, this.language);

        resolve();
      } catch (err) {
        fileHelper.saveOnFail(binFile, test.name, this.language);

        err.file = `${snippetPath.split('src/')[1]}.${config.languages[this.language].ext}`;
        logger.reportNOk(test, err, this.language);

        reject();
      } finally {
        fileHelper.removeBin(binFile);

        if (test.hooks.after)
          this.runHookCommand(test.hooks.after);
      }
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
          const err = {
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
    const
      snippet = snippetPath + '.' + config.languages[this.language].ext,
      fileContent = fs.readFileSync(snippet, 'utf8');

    if (fileContent.match(/(\@todo)/g)) {
      const err = {
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
    const
      snippet = snippetPath + '.' + config.languages[this.language].ext,
      fileContent = fs.readFileSync(snippet, 'utf8');
    if (fileContent.match(/(\@wontdo)/g)){
      const err = {
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
    childProcess.execSync(command, { stderr: 'ignore', stdio: 'ignore' })
  }

};
