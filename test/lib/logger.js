const color = require('colors/safe');
const jsonfile = require('jsonfile');
const path = require('path');
const fs = require('fs');

class Logger {
  reportOk(test) {
    this.reportToJson(test, false);
    console.log(
      color.green("✔"), color.green(test.name + ': ' + test.description)
    )
  }

  reportNOk(test, err) {
    this.reportToJson(test, err);
    console.log(color.red("✗"), color.red(test.name + ': ' + test.description + ' '))
    if (err) {
      console.log(color.red('    ' + err.code))
      console.log(color.red('    EXPECTED :'), test.expect)
      console.log(color.red('    GOT      :'), err.actual)
    }
  }
  
  reportLintNOk(test, err) {
    this.reportToJson(test, err);
    console.log(color.red("✗"), color.red(test.name + ': ' + test.description + ' '))
    if (err) {
      console.log(color.red('    ' + err.code))
      console.log(color.red('    GOT      :'), err.actual)
    }
  }
  
  reportToJson(test, err) {
    let 
      reportFile = path.join(__dirname, '../../reports/') + 'report.json',
      report = {};
    
    if(fs.existsSync(reportFile)) {
      report = jsonfile.readFileSync(reportFile);
    }
    
    report[test.name] = {
      test : test,
      status: (err) ? 'fail' : 'success',
      error: (err) ? {code: err.code, got: err.actual} : {}
    };
    
    jsonfile.writeFileSync(reportFile, report);
  }
}

module.exports = new Logger();