const
  fileHelper = require('../helpers/file'),
  nexpect = require('nexpect'),
  fs = require('fs'),
  path = require('path'),
  indentString = require('indent-string'),
  sanitize = require('sanitize-filename'),
  childProcess = require('child_process'),
  Logger = require('../helpers/logger'),
  TestError = require('../helpers/testResult');

module.exports = class GenericRunner {
  constructor() {
    if (new.target === GenericRunner) {
      throw new TypeError('Cannot construct GenericRunner instances directly');
    }

    this.logger = new Logger();
  }

  async run(snippet) {
    snippet.checkIfTodo();
    snippet.checkIfWontDo();

    snippet.render();

    try {
      if (snippet.hooks.before) {
        this.runHookCommand(snippet.hooks.before);
      }

      await this.lintExpect(snippet.renderedSnippetPath);
      await this.runExpect(snippet.renderedSnippetPath, snippet.expected);

      this.logger.reportOk(snippet);

      // Remove the generated files only if test succeed
      snippet.clean();
    } catch (e) {
      // Save renderedSnippet to display it in the web view
      snippet.saveRendered();

      e.file = snippet.snippetFile.split('src/')[1];
      this.logger.reportKO(snippet, e);
    } finally {
      if (snippet.hooks.after) {
        this.runHookCommand(snippet.hooks.after);
      }
    }
  }

  async runExpect(generatedFilePath, expected) {
    return new Promise((resolve, reject) => {
      nexpect
        .spawn(`${this.runCommand} ${generatedFilePath}`, { stream: 'all' })
        .wait(expected, result => {
          if (result === expected) {
            resolve()
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

          if (output.includes(expected)) {
            resolve()
            return;
          }

          const err = {
            code: 'ERR_ASSERTION',
            actual: output
          };
          reject(err);
        });
      })
  }

  lintExpect(generatedFilePath) {
    return new Promise((resolve, reject) => {
      nexpect
        .spawn(`${this.lintCommand} ${generatedFilePath}`, { stream: 'all' })
        .wait(this.expectedLintSuccess)
        .run(error => {
          if (error) {
            reject(error);
            return;
          }

          resolve();
        });
    });
  }

  runHookCommand(command) {
    childProcess.execSync(command, { stderr: 'ignore', stdio: 'ignore' });
  }
};
