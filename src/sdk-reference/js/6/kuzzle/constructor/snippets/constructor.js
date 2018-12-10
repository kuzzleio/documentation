const options = {
  host: 'localhost',
  autoResubscribe: false
};

// Instantiate the SDK with the websocket protocol
const
  kuzzleWs = new Kuzzle(
    new Websocket(options)
  );

// Instantiate the SDK with the http protocol
const
  kuzzleHttp = new Kuzzle(
    new Http(options)
  );
