const read = require('read-yaml');
const path = require('path');

module.exports = {
  get() {
    return read.sync(
      path.join(__dirname, './config.yml')
    );
  },

  getLanguages(config) {
    return Object.values(config.languages).map(language => language.fullname);
  },

  getSdk(language) {
    const config = this.get();

    switch (language) {
      case 'js':
        console.log(`${config.languages[language].sdk_url}#${config.languages[language].sdk_branch}`);
        break;
      case 'go':
        console.log(`${config.languages[language].sdk_url} -b ${config.languages[language].sdk_branch}`);
        break;
      default:
        break;
    }

  }
}
