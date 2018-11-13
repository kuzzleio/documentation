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
  constructor(language) {
    this.language = language;

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
    if (this.report[snippet.name] && process.env.TRAVIS) {
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
    switch (result.code) {
      case 'SUCCESS':
        console.log(
          blue(`[${snippet.language}] `),
          green('✔'),
          green(`${snippet.name}: ${snippet.description}`)
        );
        break;
      default:
        console.log(
          blue(`[${snippet.language}] `),
          red('✗'),
          red(`${snippet.name}: ${snippet.description}`)
        );
        console.log(red('        CODE    :'), result.code);
        console.log(red('        FILE    :'), result.file);
        if (result.code === 'ERR_ASSERTION') {
          console.log(red('        EXPECTED:'), result.expected || snippet.expected);
          console.log(red('        GOT     :'), result.actual);
        } else if (result.code === 'ERR_ORDER') {
          console.log(red('        THIS RESULT: '), result.after);
          console.log(red('        CAME BEFORE: '), result.before);
        } else {
          console.log(red('        ERROR   :'), result.actual);
        }


        console.log(
          blue(`[${snippet.language}] `),
          'You can run the snippet locally with the following command:'
        );
        console.log(
          blue(`[${snippet.language}] `),
          snippet.getLocalCommand()
        );
        break;
    }

    this.addToReport(snippet, result);
  }

  log(message, status) {
    const statusMessage = (() => {
      switch (status) {
        case true:
          return green('✔');
        case false:
          return red('✗');
        default:
          return '';
      }
    })();

    console.log(
      blue(`[${this.language}] `),
      message,
      statusMessage
    );
  }
}

module.exports = Logger;
