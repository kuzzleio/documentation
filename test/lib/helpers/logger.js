const
  {
    green,
    red,
    blue,
    yellow
  } = require('colors/safe'),
  jsonfile = require('jsonfile'),
  path = require('path'),
  fs = require('fs');

/* eslint-disable no-console */

class Logger {
  constructor() {
    this.reportFile = `${path.join(__dirname, '../../../reports/')}report.json`;

    this.report = {};

    if (fs.existsSync(this.reportFile)) {
      try {
        this.report = jsonfile.readFileSync(this.reportFile);
      } catch (e) {
        if (! (e instanceof SyntaxError)) {
          throw e;
        }
      }
    }
  }

  addToReport(snippet, result) {
    // Do not display this warning locally because we often run the tests multiple time
    if (this.report[snippet.name] && process.env['TRAVIS']) {
      console.log(
        yellow('/!\\'),
        ` Duplicate snippet name: ${snippet.name}`
      );
    }

    let status;

    switch (result.code) {
      case 'SUCCESS':
        status = 'Success';
        break;
      case 'TODO':
        status = 'Todo';
        break;
      case 'WONTDO':
        status = 'Wontdo';
        break;
      default:
        status = 'Fail';
        break;
    }

    this.report[snippet.name] = {
      status,
      language: snippet.language,
      test: snippet.testDefinition,
      datetime: new Date().toLocaleString(),
      error: result.code !== 'SUCCESS' ? { code: result.code, got: result.actual } : {},
      file: (typeof result.file !== 'undefined') ? result.file : ''
    };
  }

  writeReport() {
    jsonfile.writeFileSync(this.reportFile, this.report);
  }

  reportResult(snippet, result) {
    if (result.code === 'SUCCESS') {
      console.log(
        blue(`[${snippet.language}] `),
        green('✔'),
        green(`${snippet.name}: ${snippet.description}`)
      );
    } else {
      console.log(
        blue(`[${snippet.language}] `),
        red('✗'),
        red(`${snippet.name}: ${snippet.description}`)
      );
      console.log(red('        ' + result.code));
      console.log(red('        EXPECTED:'), snippet.expected);
      console.log(red('        GOT     :'), result.actual);
    }

    this.addToReport(snippet, result);
  }
}

module.exports = Logger;
