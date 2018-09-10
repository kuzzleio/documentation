const
  BaseRunner = require('./baseRunner'),
  path = require('path'),
  nexpect = require('nexpect'),
  { execute } = require('../helpers/utils'),
  TestResult = require('../helpers/testResult');

module.exports = class JsRunner extends BaseRunner {
  constructor() {
    super();

    this.lintConfig = path.join(__dirname, '../../linters/eslint.json');
    this.lintCommand = './node_modules/.bin/eslint';
    this.lintOptions = ['-c', this.lintConfig];
  }

  async runExpect(snippet) {
    this.nexpectCommand = `node ${snippet.renderedSnippetPath}`;

    await super.runExpect(snippet);
  }

  async lint(snippet) {
    await super.lint(snippet, this.lintOptions.concat(snippet.renderedSnippetPath));
  }
};
