const
  fs = require('fs'),
  { execute } = require('../helpers/utils'),
  BaseRunner = require('./baseRunner'),
  TestResult = require('../helpers/testResult');

module.exports = class CsharpRunner extends BaseRunner {
  constructor(sdk) {
    super(sdk);
    this.compileOptions = [`-r:/app/${this.sdk.sdkDir}/kuzzlesdk-0.0.1.dll`];
    this.lintCommand = 'echo "none"';
    this.lintOptions = [];
    this.executablePath = '';
  }

  async runSnippet(snippet) {
    const executableFile = `${snippet.renderedSnippetPath.split('.')[0]}.exe`
    process.env.LD_LIBRARY_PATH = `./${this.sdk.sdkDir}/`;
    this.snippetCommand = `mono ${executableFile}`;

    try {
      await execute('mcs', this.compileOptions.concat([`-out:${executableFile}`, snippet.renderedSnippetPath]));
    } catch (e) {
      const res = {
        code: 'COMPILATION_FAIL',
        actual: e.message
      };
      throw new TestResult(res);
    }

    await super.runSnippet(snippet);
  }

  async lint(snippet) {
    // No linter for now
    // await super.lint(snippet, this.lintOptions.concat(snippet.renderedSnippetPath));
  }

  clean(snippet) {
    super.clean(snippet);
    fs.unlinkSync(this.executablePath);
  }
};
