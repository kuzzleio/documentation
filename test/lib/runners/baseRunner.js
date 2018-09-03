const
  fileHelper = require('../helpers/file'),
  nexpect = require('nexpect'),
  fs = require('fs'),
  path = require('path'),
  indentString = require('indent-string'),
  sanitize = require('sanitize-filename'),
  childProcess = require('child_process'),
  TestResult = require('../helpers/testResult');

module.exports = class BaseRunner {

  async run(snippet) {
    snippet.checkIfTodo();
    snippet.checkIfWontDo();

    snippet.render();

    try {
      if (snippet.hooks.before) {
        this.runHookCommand(snippet.hooks.before);
      }

      await this.lintExpect(snippet);
      await this.runExpect(snippet);

      // Remove the generated files only if test succeed
      snippet.clean();
    } catch (e) {
      // Save renderedSnippet to display it in the web view
      snippet.saveRendered();

      e.file = snippet.snippetFile.split('src/')[1];

      throw e;
    } finally {
      if (snippet.hooks.after) {
        this.runHookCommand(snippet.hooks.after);
      }
    }
  }

  async runExpect(snippet) {
    return new Promise((resolve, reject) => {
      nexpect
        .spawn(`${this.runCommand} ${snippet.renderedSnippetPath}`, { stream: 'all' })
        .wait(snippet.expected, result => {
          if (result === snippet.expected) {
            resolve()
            return;
          }

          const res = {
            code: 'ERR_ASSERTION',
            actual: result
          };
          reject(new TestResult(res));
        })
        .run((error, output) => {
          if (error) {
            reject(error);
            return;
          }

          if (output.includes(snippet.expected)) {
            resolve()
            return;
          }

          const res = {
            code: 'ERR_ASSERTION',
            actual: output
          };
          reject(new TestResult(res));
        });
      })
  }

  lintExpect(snippet) {
    return new Promise((resolve, reject) => {
      nexpect
        .spawn(`${this.lintCommand} ${snippet.renderedSnippetPath}`, { stream: 'all' })
        .wait(this.expectedLintSuccess)
        .run(error => {
          if (error) {
            const result = {
              code: 'ERR_ASSERTION',
              actual: error
            };
            reject(new TestResult(result));
            return;
          }

          resolve();
        });
    });
  }

  runHookCommand(command) {
    try {
      childProcess.execSync(command, { stderr: 'ignore', stdio: 'ignore' });
    } catch (e) {
      const result = {
        code: 'HOOK_FAILED',
        actual: e.message
      };

      throw new TestResult(result);
    }
  }
};
