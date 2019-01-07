// Loads the SocketIO protocol
const
  {
    SocketIO
  } = require('kuzzle-sdk');

const options = {
  autoReconnect: false
};

// Instantiates the SocketIO protocol
const socketIOProtocol = new SocketIO('kuzzle', options);
