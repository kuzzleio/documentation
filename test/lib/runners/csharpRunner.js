const
  fs = require('fs'),
  { execute } = require('../helpers/utils'),
  BaseRunner = require('./baseRunner'),
  TestResult = require('../helpers/testResult');

module.exports = class CsharpRunner extends BaseRunner {
  constructor(sdk) {
    super(sdk);
    this.lintCommand = 'echo "none"';
    this.lintOptions = [];
    this.ext = 'cs';
  }

  async runSnippet(snippet) {
    this.snippetCommand = `dotnet script ${snippet.renderedSnippetPath}`;

    await super.runSnippet(snippet);
  }

  async lint(snippet) {
    // No linter for now
    // await super.lint(snippet, this.lintOptions.concat(snippet.renderedSnippetPath));
  }

  clean(snippet) {
    super.clean(snippet);
  }
};
