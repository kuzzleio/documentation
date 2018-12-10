const options = {
  host: 'localhost',
  autoResubscribe: false
};

// Instantiate the SDK with the specified protocol
const
  kuzzle = new Kuzzle(
    new Websocket(options)
  );
