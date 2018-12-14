const options = {
  host: 'localhost',
  autoResubscribe: false
};

// Instantiates the SDK with the websocket protocol
const
  kuzzleWs = new Kuzzle(
    new Websocket(options)
  );

// Instantiates the SDK with the http protocol
const
  kuzzleHttp = new Kuzzle(
    new Http(options)
  );
