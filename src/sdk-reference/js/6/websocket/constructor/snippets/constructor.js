// Loads the WebSocket protocol
const
  {
    WebSocket
  } = require('kuzzle-sdk');

const options = {
  autoReconnect: false
};

// Instantiates the websocket protocol
const websocketProtocol = new WebSocket('kuzzle', options);
