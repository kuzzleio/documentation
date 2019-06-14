// Loads the WebSocket protocol
const
  {
    Kuzzle,
    WebSocket
  } = require('kuzzle-sdk');

// Use secure SSL connection to Kuzzle
const options = {
  sslConnection: false
};

// Instantiates the WebSocket protocol
const websocketProtocol = new WebSocket('kuzzle', options);

// Use it with Kuzzle
const kuzzle = new Kuzzle(websocketProtocol);