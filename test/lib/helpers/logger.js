const
  color = require('colors/safe'),
  jsonfile = require('jsonfile'),
  path = require('path'),
  fs = require('fs');

/* eslint-disable no-console */

class Logger {
  reportOk(snippet) {
    this.reportToJson(snippet, { code: 'SUCCESS' });

    console.log(
      color.green('✔'),
      color.green(`${snippet.name}: ${snippet.description}`)
    );
  }

  reportKO(snippet, result) {
    console.log(`LANGUAGE: ${snippet.language}`);

    this.reportToJson(snippet, result);

    console.log(color.red('✗'), color.red(`${snippet.name}: ${snippet.description}`));
    if (result) {
      console.log(color.red('    ' + result.code));
      console.log(color.red('    EXPECTED:'), snippet.expected);
      console.log(color.red('    GOT     :'), result.actual);
    }
  }

  reportToJson(snippet, result) {
    const reportFile = `${path.join(__dirname, '../../../reports/')}report.json`;

    let
      report = {},
      status;

    if (fs.existsSync(reportFile)) {
      report = jsonfile.readFileSync(reportFile);
    }

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

    report[snippet.name] = {
      status,
      language: snippet.language,
      test: snippet.testDefinition,
      datetime: new Date().toLocaleString(),
      error: result.code !== 'SUCCESS' ? { code: result.code, got: result.actual } : {},
      file: (typeof result.file !== 'undefined') ? result.file : ''
    };

    jsonfile.writeFileSync(reportFile, report);
  }
}

module.exports = Logger;
