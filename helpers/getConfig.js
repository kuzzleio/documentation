const read = require('read-yaml');
const path = require('path');

module.exports = {
  get() {
    return read.sync(
      path.join(__dirname, '../config.yml')
    ); 
  },
  
  getLanguages(config) {
    let languages = [];
    for (let k in config.languages) {
      languages.push(config.languages[k].fullname);
    }
    return languages;
  }
}