// Loads the Http protocol
const
  {
    Http
  } = require('kuzzle-sdk');

const options = {
  sslConnection: false
};

// Instantiates the Http protocol
const httpProtocol = new Http('kuzzle', options);
