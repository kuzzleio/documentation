const
  Tester = require('./tester'),
  path = require('path'),
  nexpect = require('nexpect');

module.exports = class JsTester extends Tester {
  constructor() {
    super();
    let lintConfig = path.join(__dirname, '../../linters/eslint.json');
    this.language = 'js';
    this.runCommand = 'node';
    this.lintCommand = `eslint -c ${lintConfig}`;
  }

  lintExpect(binFile) {
    return new Promise((resolve, reject) => {
      nexpect
        .spawn(`${this.lintCommand} ${binFile}`, { stream: 'all' })
        .wait('')
        .run((error, output) => {
          if (error) {
            resolve();
            return;
          }

          const err = {
            code: 'LINTER_ERROR',
            actual: output.join('\n')
          };
          reject(err);
        });
    });
  }
};
