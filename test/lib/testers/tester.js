const
  fileHelper = require('../helpers/file'),
  nexpect = require('nexpect'),
  fs = require('fs'),
  childProcess = require('child_process'),
  logger = require('../helpers/logger'),
  config = require('../../../getConfig').get();

module.exports = class Tester {

  constructor () {
    if (new.target === Tester) {
      throw new TypeError('Cannot construct Tester instances directly');
    }
  }

  runOneTest(test, snippetPath) {
    return new Promise(async (resolve, reject) => {
      if (this.isTodo(snippetPath, test) || this.isWontdo(snippetPath, test)) {
        resolve();
        return;
      }

      const generatedFilePath = fileHelper.injectSnippet(test, snippetPath, this.language);
      if (! generatedFilePath) {
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
        if (test.hooks.before) {
          this.runHookCommand(test.hooks.before);
        }

        await this.lintExpect(generatedFilePath);
        await this.runExpect(generatedFilePath, test.expect);

        logger.reportOk(test, this.language);

        // Remove the generated files only if test succeed
        this.clean(generatedFilePath);
        resolve();
      }
      catch (err) {
        fileHelper.saveOnFail(generatedFilePath, test.name, this.language);

        err.file = `${snippetPath.split('src/')[1]}.${config.languages[this.language].ext}`;
        logger.reportNOk(test, err, this.language);

        reject();
      }
      finally {
        if (test.hooks.after) {
          this.runHookCommand(test.hooks.after);
        }
      }
    });
  }

  runExpect(generatedFilePath, expected) {
    return new Promise((resolve, reject) => {
      nexpect
        .spawn(`${this.runCommand} ${generatedFilePath}`, { stream: 'all' })
        .wait(expected, result => {
          if (result === expected) {
            resolve();
            return;
          }

          const err = {
            code: 'ERR_ASSERTION',
            actual: result
          };
          reject(err);
        })
        .run((error, output) => {
          if (error) {
            reject(error);
            return;
          }

          if(output.includes(expected)) {
            resolve();
            return;
          }

          const err = {
            code: 'ERR_ASSERTION',
            actual: output
          };
          reject(err);
        });
    });
  }

  lintExpect(generatedFilePath) {
    return new Promise((resolve, reject) => {
      nexpect
        .spawn(`${this.lintCommand} ${generatedFilePath}`, { stream: 'all' })
        .wait(this.expectedLintSuccess)
        .run((error) => {
          if (error) {
            reject(error);
            return;
          }

          resolve();
        });
    });
  }

  isTodo(snippetPath, test) {
    const
      snippet = `${snippetPath}.${config.languages[this.language].ext}`,
      fileContent = fs.readFileSync(snippet, 'utf8');

    if (fileContent.match(/(@todo)/g)) {
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
      snippet = `${snippetPath}.${config.languages[this.language].ext}`,
      fileContent = fs.readFileSync(snippet, 'utf8');

    if (fileContent.match(/(@wontdo)/g)) {
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
    childProcess.execSync(command, { stderr: 'ignore', stdio: 'ignore' });
  }

  clean(generatedFilePath) {
    fileHelper.remove(generatedFilePath);
  }

};
