const options = {
  host: 'localhost',
  autoResubscribe: false
};

// Instantiate the SDK with an embedded protocol
const kuzzleWS = new Kuzzle('websocket', options);

// Instantiate the SDK with a custom protocol
const customProtocol = new MyCustomProtocol();
const kuzzleCustom = new Kuzzle(customProtocol, options);
