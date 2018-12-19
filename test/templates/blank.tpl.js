// load the Kuzzle SDK module
const
  {
    Kuzzle,
    Websocket,
    Http
  } = require('kuzzle-sdk');

const kuzzle = new Kuzzle(
  new Websocket({ host: 'kuzzle' })
);

[snippet-code]
console.log('Everything is ok');
