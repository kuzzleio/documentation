const color = require('colors/safe');
const jsonfile = require('jsonfile');
const path = require('path');
const fs = require('fs');

class Logger {
  
  reportOk(test, language) {
    this.reportToJson(test, false, language);
    console.log(
      color.green('✔'), color.green(`${test.name} : ${test.description}`)
    )
  }

  reportNOk(test, err, language) {
    console.log('LANGUAGE :', language);
    this.reportToJson(test, err, language);
    console.log(color.red('✗'), color.red(`${test.name} : ${test.description}`))
    if (err) {
      console.log(color.red('    ' + err.code))
      console.log(color.red('    EXPECTED :'), test.expect)
      console.log(color.red('    GOT      :'), err.actual)
    }
  }
  
  reportToJson(test, err, language) {
    let 
      reportFile = path.join(__dirname, '../../../reports/') + 'report.json',
      report = {},
      status;
    
      
    if (fs.existsSync(reportFile)) {
      report = jsonfile.readFileSync(reportFile);
    }
    switch (err.code) {
      case undefined:
        status = 'Success'
        break;
      case 'TODO':
        status = 'Todo'
        break;
      case 'WONTDO':
        status = 'Wontdo'
        break;
      default:
        status = 'Fail'
    } 
    
    report[test.name] = {
      test: test,
      language: language,
      datetime: new Date().toLocaleString(),
      status: status,
      error: (err) ? {code: err.code, got: err.actual} : {},
      file: (err && typeof err.file != 'undefined' ) ? err.file : ''
    };
    
    jsonfile.writeFileSync(reportFile, report);
  }
}

module.exports = new Logger();