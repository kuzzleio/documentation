const fs = require('fs'),
  path = require('path'),
  indentString = require('indent-string'),
  sanitize = require('sanitize-filename'),
  config = require('../../../getConfig').get();

const TEMPLATE_FOLDER = path.join(__dirname, '../../templates/'),
  SAVE_FOLDER = path.join(__dirname, '../../../reports/failed/'),
  BIN_FOLDER = path.join(__dirname, '../../bin/'),
  GENERIQUE_JAVA_CLASSNAME = 'CodeExampleGenericClass';

class FileProcess {
  injectSnippet(test, snippetPath, language) {
    if (!test) {
      const err = {
        code: 'MISSING_TEST_DESCRIPTION',
        actual: 'Missing or empty test.yml file'
      };
      return err;
    }

    const template = `${TEMPLATE_FOLDER}${test.template}.tpl.${language}`,
      snippet = `${snippetPath}.${config.languages[language].ext}`;

    //first check file exist
    if (!fs.existsSync(template)) {
      const err = {
        code: 'MISSING_TEMPLATE',
        expect: '',
        actual: `Missing template file: ${template}`
      };
      return err;
    }

    if (!fs.existsSync(snippet)) {
      const err = {
        code: 'MISSING_SNIPPET',
        expect: '',
        actual: `Missing snippet file: ${snippet}`
      };
      return err;
    }

    //get file content
    let snippetContent = fs.readFileSync(snippet, 'utf8'),
      templateContent = fs.readFileSync(template, 'utf8');

    if (!templateContent.match(/(\[snippet-code])/g)) {
      const err = {
        code: 'MISSING_TAG',
        expect: '',
        actual: 'Missing tag [snippet-code]'
      };
      return err;
    }

    //replace snippet in template
    const indentationCount = this.getIndentation(templateContent);
    const indentedSnippet = this.indentSnippet(
      snippetContent,
      indentationCount
    );

    const newContent = templateContent.replace(
        /(\[snippet-code])/g,
        indentedSnippet
      ),
      fileName = this.sanitizeFileName(test.name),
      binPath = `${BIN_FOLDER}${fileName}.${language}`;

    // JAVA hack, because filename has to be the same of the class name
    // We replace the template generique class name by the name of the test
    if (language === 'java') {
      fs.writeFileSync(binPath, this.overrideClassName(newContent, fileName));
    } else {
      fs.writeFileSync(binPath, newContent);
    }

    if (!fs.existsSync(binPath)) {
      const err = {
        code: 'MISSING_GENERATED_FILE',
        expect: test.expect,
        actual: `Missing generated file: ${binPath}`
      };
      return err;
    }

    return binPath;
  }

  indentSnippet(snippet, indentation) {
    const firstline = snippet.split('\n')[0];
    snippet = snippet.replace(firstline, '');

    return firstline + indentString(snippet, indentation);
  }

  getIndentation(template) {
    const matches = template.match(/^.*snippet-code.*$/gm);

    return matches[0].match(/^\s*/)[0].length;
  }

  saveOnFail(binFile, testName, language) {
    testName = this.sanitizeFileName(testName);
    const dest = `${SAVE_FOLDER}${testName}.${language}`;
    fs.copyFileSync(binFile, dest);

    return true;
  }

  remove(filePath) {
    fs.unlinkSync(filePath);
  }

  sanitizeFileName(fileName) {
    return sanitize(fileName)
      .replace(' ', '_')
      .toLowerCase();
  }

  overrideClassName(content, name) {
    return content.replace(GENERIQUE_JAVA_CLASSNAME, name);
  }
}

module.exports = new FileProcess();
