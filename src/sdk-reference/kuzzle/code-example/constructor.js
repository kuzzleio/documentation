// Instantiate the SDK with an embedded protocol
const kuzzleWS = new Kuzzle('websocket', { host: 'localhost' });

// Instantiate the SDK with a custom protocol
const customProtocol = new MyCustomProtocol();
const kuzzleCustom = new Kuzzle(customProtocol, { host: 'localhost' });
