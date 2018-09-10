const
  fs = require('fs'),
  { execute } = require('../helpers/utils'),
  BaseRunner = require('./baseRunner'),
  TestResult = require('../helpers/testResult');

module.exports = class CppRunner extends BaseRunner {
  constructor() {
    super();
    this.sdkPath = 'test/bin/sdk-cpp';
    this.compileOptions = ['-std=c++11', `-I${this.sdkPath}/include`, `-L${this.sdkPath}/lib`, '-lkuzzlesdk', '-lpthread'];
    this.lintCommand = 'cpplint';
    this.lintOptions = ['--filter=-legal/copyright,-whitespace/line_length'];
    this.executablePath = '';
  }

  async runExpect(snippet) {
    process.env.LD_LIBRARY_PATH = `./${this.sdkPath}/lib`;
    this.nexpectCommand = snippet.renderedSnippetPath.split('.')[0];

    try {
      await execute('g++', this.compileOptions.concat(['-o', this.nexpectCommand, snippet.renderedSnippetPath]));
    } catch (e) {
      const res = {
        code: 'COMPILATION_FAIL',
        actual: e.message
      };
      throw new TestResult(res);
    }

    await super.runExpect(snippet);
  }

  async lint(snippet) {
    await super.lint(snippet, this.lintOptions.concat(snippet.renderedSnippetPath));
  }

  clean(snippet) {
    fs.unlinkSync(snippet.renderedSnippetPath);
    fs.unlinkSync(this.executablePath);
  }
};
