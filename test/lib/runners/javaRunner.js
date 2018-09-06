const
  fs = require('fs'),
  path = require('path'),
  nexpect = require('nexpect'),
  BaseRunner = require('./baseRunner');

module.exports = class JavaTester extends BaseRunner {
  constructor() {
    super();
    this.runCommand = 'java -cp ./test/bin/kuzzlesdk-java.jar:./test/bin';
    this.lintCommand = 'javac';
    this.lintOptions = ['-cp', 'test/bin/kuzzlesdk-java.jar'];
  }

  async runExpect(snippet) {
    const generatedFilename = path.basename(snippet.renderedSnippetPath, '.java');
    this.nexpectCommand = `${this.runCommand} ${generatedFilename}`;

    await super.runExpect(snippet);
  }

  async lint(snippet) {
    await super.lint(snippet, this.lintOptions.concat(snippet.renderedSnippetPath));
  }

  clean(snippet) {
    fs.unlinkSync(snippet.renderedSnippetPath);
    fs.unlinkSync(snippet.renderedSnippetPath.replace('.java', '.class'));
  }
};
