const
  fs = require('fs'),
  path = require('path'),
  readYaml = require('read-yaml'),
  indentString = require('indent-string'),
  sanitize = require('sanitize-filename'),
  childProcess = require('child_process'),
  TestResult = require('./helpers/testResult');

const
  TEMPLATES_DIR = path.join(__dirname, '../templates/'),
  RENDERED_SNIPPETS_DIR = path.join(__dirname, '../bin/'),
  SAVE_FAIL_DIR = path.join(__dirname, '../../reports/failed/'),
  GENERIC_JAVA_CLASSNAME = 'CodeExampleGenericClass';

class Snippet {
  constructor(testFile, language) {
    this.testFile = testFile;
    this.language = language;

    this.testDefinition = readYaml.sync(testFile);
    if (Object.keys(this.testDefinition).length === 0) {
      const result = {
        code: 'MISSING_TEST_DESCRIPTION',
        actual: 'Missing or empty test.yml file'
      };

      throw new TestResult(result);
    }
    for (const [attribute, value] of Object.entries(this.testDefinition)) {
      this[attribute] = value;
    }

    this.templateFile = `${TEMPLATES_DIR}${this.template}.tpl.${this.language}`
    if (! fs.existsSync(this.templateFile)) {
      const result = {
        code: 'MISSING_TEMPLATE',
        actual: `Missing template file: ${this.templateFile}`
      };

      throw new TestResult(result);
    }

    this.snippetFile = testFile.replace('.test.yml', `.${this.language}`);
    if (! fs.existsSync(this.snippetFile)) {
      const result = {
        code: 'MISSING_SNIPPET',
        actual: `Missing snippet file: ${this.snippetFile}`
      };

      throw new TestResult(result);
    }

    this.snippetContent = fs.readFileSync(this.snippetFile, 'utf8');
  }

  /**
   * Render the snippet code inside the template and write it in file
   *
   */
  render() {
    this.templateContent = fs.readFileSync(this.templateFile, 'utf8')
    if (this.templateContent.indexOf('[snippet-code]') === -1) {
      const result = {
        code: 'MISSING_TAG',
        actual: 'Missing tag [snippet-code]'
      };

      throw new TestResult(result);
    }

    const
      indentedSnippet = this._getIndentedSnippet(),
      snippetName = this._sanitizeFileName(this.name),
      renderedSnippet = this.templateContent.replace(/(\[snippet-code])/g, indentedSnippet);

    this.renderedSnippetPath = `${RENDERED_SNIPPETS_DIR}${snippetName}.${this.language}`;

    // JAVA hack, because filename has to be the same of the class name
    // We replace the template generique class name by the name of the test
    if (this.language === 'java') {
      fs.writeFileSync(this.renderedSnippetPath, this._overrideClassName(renderedSnippet, snippetName));
    } else {
      fs.writeFileSync(this.renderedSnippetPath, renderedSnippet);
    }

    if (! fs.existsSync(this.renderedSnippetPath)) {
      const result = {
        code: 'MISSING_GENERATED_FILE',
        actual: `Missing generated file: ${this.renderedSnippetPath}`
      };

      throw new TestResult(result);
    }
  }

  checkIfTodo() {
    if (this.snippetContent.indexOf('@todo') === -1) {
      return;
    }

    const result = {
      code: 'TODO',
      file: this.snippetFile
    };

    throw new TestResult(result);
  }

  checkIfWontDo() {
    if (this.snippetContent.indexOf('@wontdo') === -1) {
      return;
    }

    const result = {
      code: 'WONTDO',
      file: this.snippetFile
    };

    throw new TestResult(result)
  }

  saveRendered() {
    const dest = `${SAVE_FAIL_DIR}${this.name}.${this.language}`;
    fs.copyFileSync(this.snippetFile, dest);
  }

  clean() {
    fs.unlinkSync(this.renderedSnippetPath);
  }

  _getIndentedSnippet() {
    const
      matches = this.templateContent.match(/^.*snippet-code.*$/gm),
      snippetIndentation = matches[0].match(/^\s*/)[0].length,
      firstline = this.snippetContent.split('\n')[0];

    return firstline + indentString(this.snippetContent.replace(firstline, ''), snippetIndentation);
  }

  _sanitizeFileName(fileName) {
    return sanitize(fileName).replace(' ', '_').toLowerCase();
  }

  _overrideClassName(content, name) {
    return content.replace(GENERIC_JAVA_CLASSNAME, name);
  }

}

module.exports = Snippet;
