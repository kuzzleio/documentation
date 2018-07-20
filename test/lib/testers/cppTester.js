const
  fileHelper = require('../helpers/file'),
  Tester = require('./tester'),
  nexpect = require('nexpect'),
  childProcess = require('child_process');

module.exports = class CppTester extends Tester {
  constructor() {
    super();
    this.sdkPath = 'test/bin/sdk-cpp';
    this.language = 'cpp';
    this.compileCommand = `g++ -I${this.sdkPath}/include -L${this.sdkPath}/lib -lkuzzlesdk -lpthread`;
    this.runCommand = '';
    this.lintCommand = 'cpplint --filter=-legal/copyright,-whitespace/line_length';
    this.executablePath = '';
  }

  runExpect(generatedFilePath, expected) {
    process.env.LD_LIBRARY_PATH = `./${this.sdkPath}/lib`;
    this.executablePath = generatedFilePath.split('.')[0];

    childProcess.execSync(`${this.compileCommand} -o ${this.executablePath} ${generatedFilePath}`);

    return new Promise((resolve, reject) => {
      nexpect
        .spawn(`${this.executablePath}`, { stream: 'stdout' })
        .wait(expected, result => {
          if (result === expected) {
            resolve();
            return;
          }

          const err = {
            code: 'ERR_ASSERTION',
            actual: result
          };
          reject(err);
        })
        .run((error, output) => {
          if (error) {
            reject(error);
            return;
          }

          if (output.includes(expected)) {
            resolve();
            return;
          }

          const err = {
            code: 'ERR_ASSERTION',
            actual: output
          };
          reject(err);
        });
    });
  }

  lintExpect(generatedFilePath) {
    const expected = `Done processing ${generatedFilePath}`;

    return new Promise((resolve, reject) => {
      nexpect
        .spawn(`${this.lintCommand} ${generatedFilePath}`, { stream: 'all' })
        .wait(expected)
        .run((error, output) => {
          if (! error) {
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

  clean(generatedFilePath) {
    fileHelper.remove(generatedFilePath);
    fileHelper.remove(this.executablePath);
  }
};
