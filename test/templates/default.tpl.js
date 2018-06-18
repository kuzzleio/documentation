// load the Kuzzle SDK module
const Kuzzle = require('kuzzle-sdk')

// instantiate a Kuzzle client, this will automatically connect to the Kuzzle server
const kuzzle = new Kuzzle('kuzzle', { defaultIndex: 'playground' })

// add a listener to detect any connection problems
kuzzle.on('networkError', function (error) {
  console.error('Network Error:' + error);
})

[snippet-code]
