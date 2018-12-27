const
  jsSdk = require('./jsSdk');

class JsWebSdk extends jsSdk {
  constructor(version) {
    super(version);
    
    this.name = 'jsWeb';
    this.ext = 'html';
  }
}

module.exports = JsWebSdk;
