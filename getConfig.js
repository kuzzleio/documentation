const read = require('read-yaml');
const path = require('path');

module.exports = {
  get() {
    return read.sync(
      path.join(__dirname, './config.yml')
    ); 
  },
  
  getLanguages(config) {
    let languages = [];
    for (let k in config.languages) {
      languages.push(config.languages[k].fullname);
    }
    return languages;
  },
  
  getSdk(language) {
    config = this.get();
    switch (language) {
      case 'js':
        console.log(`${config.languages[language].sdk_url}#${config.languages[language].sdk_branch}`);
        break;
      case 'go':
        console.log(`${config.languages[language].sdk_url} -b ${config.languages[language].sdk_branch}`);
        break;
      case 'java':
        console.log(`${config.languages[language].sdk_url} -b ${config.languages[language].sdk_branch}`);
        break;
      default:
        
        break;
    }
    
  }
}