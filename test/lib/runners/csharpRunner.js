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

<<<<<<< HEAD
  async runExpect(snippet) {
    this.executablePath = `${snippet.renderedSnippetPath.split('.')[0]}.exe`;
    process.env.LD_LIBRARY_PATH = `./${this.sdk.sdkDir}/`;
    this.nexpectCommand = `mono ${this.executablePath}`;

    try {
      await execute('mcs', this.compileOptions.concat([`-out:${this.executablePath}`, snippet.renderedSnippetPath]));
=======
  async runSnippet(snippet) {
    const executableFile = `${snippet.renderedSnippetPath.split('.')[0]}.exe`
    process.env.LD_LIBRARY_PATH = `./${this.sdk.sdkDir}/`;
    this.snippetCommand = `mono ${executableFile}`;

    try {
      await execute('mcs', this.compileOptions.concat([`-out:${executableFile}`, snippet.renderedSnippetPath]));
>>>>>>> origin/master
    } catch (e) {
      const res = {
        code: 'COMPILATION_FAIL',
        actual: e.message
      };
      throw new TestResult(res);
    }

<<<<<<< HEAD
    await super.runExpect(snippet);
  }

  async lint() {
=======
    await super.runSnippet(snippet);
  }

  async lint(snippet) {
>>>>>>> origin/master
    // No linter for now
    // await super.lint(snippet, this.lintOptions.concat(snippet.renderedSnippetPath));
  }

  clean(snippet) {
<<<<<<< HEAD
=======
    fs.unlinkSync(snippet.renderedSnippetPath);
>>>>>>> origin/master
    fs.unlinkSync(this.executablePath);
  }
};
