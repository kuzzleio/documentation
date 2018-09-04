const
  BaseRunner = require('./baseRunner'),
  path = require('path'),
  nexpect = require('nexpect'),
  execute = require('../helpers/execute'),
  TestResult = require('../helpers/testResult');

module.exports = class JsRunner extends BaseRunner {
  constructor() {
    super();

    this.lintConfig = path.join(__dirname, '../../linters/eslint.json');
    this.language = 'js';
    this.runCommand = 'node';
    this.lintCommand = `./node_modules/.bin/eslint`;
  }

  async lintExpect(snippet) {
    try {
      await execute(this.lintCommand, ['-c', this.lintConfig, snippet.renderedSnippetPath]);
    } catch (e) {
      const result = {
        code: 'ERR_LINTER',
        actual: e.message
      };

      throw new TestResult(result);
    }
  }
};
