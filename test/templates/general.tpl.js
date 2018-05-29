const Kuzzle = require('kuzzle-sdk');
let kuzzle = new Kuzzle('kuzzleHost');

 
  return kuzzle.connect()
    .then(kuzzle => {
        [snippet-code]   
        console.log("test ok")
    });