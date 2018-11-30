const
  nexpect = require('nexpect'),
  childProcess = require('child_process'),
  { execute } = require('../helpers/utils'),
  TestResult = require('../helpers/testResult');

module.exports = class BaseRunner {
  constructor(sdk) {
    this.sdk = sdk;
    this.nexpectCommand = '';
    this.lintCommand = '';
    this.lintOptions = [];
  }

  async run(snippet) {
    snippet.render();

    try {
      if (snippet.hooks.before) {
        this.runHookCommand(snippet.hooks.before);
      }

      await this.lint(snippet);
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
        .spawn(this.nexpectCommand, { stream: 'all' })
        .run((error, stdout, cerr) => {
          if (error) {
            const res = {
              code: 'ERR_ASSERTION',
              actual: error.actual
            };
            reject(new TestResult(res));

            return;
          }

          const
            expected = Array.isArray(snippet.expected) ? snippet.expected : [snippet.expected];

          let
            lastIndex = -1,
            previous = null;

          for (const e of expected) {
            let
              match = null,
              index;

            for (index = 0; index < stdout.length && match === null; index++) {
              match = stdout[index].match(e);
            }

            if (match === null) {
              return reject(new TestResult({
                code: 'ERR_ASSERTION',
                expected: e,
                actual: stdout
              }));
            }

            if (index < lastIndex) {
              return reject(new TestResult({
                code: 'ERR_ORDER',
                actualOrder: [previous, e],
                actual: stdout
              }));
            }

            lastIndex = index;
            previous = e;
          }

          return resolve();
        });
    });
  }

  async lint(snippet, lintOptions) {
    try {
      await execute(this.lintCommand, lintOptions);
    } catch (e) {
      const result = {
        code: 'ERR_LINTER',
        actual: e.message
      };

      throw new TestResult(result);
    }
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
