const color = require('colors/safe')

class Logger {
  reportOk(test) {
    console.log(
      color.green("✔"), color.green(test.name + ': ' + test.description)
    )
  }

  reportNOk(test, err) {
    console.log(color.red("✗"), color.red(test.name + ': ' + test.description + ' '))
    if (err) {
      console.log(color.red('    ' + err.code))
      console.log(color.red('    EXPECTED :'), test.expect)
      console.log(color.red('    GOT      :'), err.actual)
    }
  }
  
  reportLintNOk(test, err) {
    console.log(color.red("✗"), color.red(test.name + ': ' + test.description + ' '))
    if (err) {
      console.log(color.red('    ' + err.code))
      console.log(color.red('    GOT      :'), err.actual)
    }
  }  
}

module.exports = new Logger();