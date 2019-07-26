const clc = require('cli-color');

class ColorOutput {
  constructor () {

    this.format = {
      error: clc.red,
      warn: clc.yellow,
      notice: clc.cyanBright,
      ok: clc.green.bold,
      question: clc.whiteBright
    };
  }

  /* eslint-disable no-console */
  error(str) {
    console.error(this.format.error(`[✖] ${str}`));
  }

  warn(str) {
    console.warn(this.format.warn(`[ℹ] ${str}`));
  }

  notice(str) {
    console.log(this.format.notice(`[ℹ] ${str}`));
  }

  question(str) {
    console.log(this.format.question(str));
  }

  ok(str) {
    console.log(this.format.ok(`[✔] ${str}`));
  }

  /* eslint-enable no-console */
}

module.exports = new ColorOutput();
