const read = require('read-yaml')
const path = require('path')

module.exports = {
  get() {
    return read.sync(
      path.join(__dirname, '../config.yml')
    ); 
  }
  
}