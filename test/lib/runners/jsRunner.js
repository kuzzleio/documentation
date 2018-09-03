const
  BaseRunner = require('./baseRunner'),
  path = require('path'),
  nexpect = require('nexpect'),
  { spawnSync } = require('child_process');
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
    const { status, stdout } = spawnSync(this.lintCommand, ['-c', this.lintConfig, snippet.renderedSnippetPath]);

    if (status !== 0) {
      const result = {
        code: 'ERR_LINTER',
        actual: stdout.toString()
      };

      throw new TestResult(result);
    }
  }
};
