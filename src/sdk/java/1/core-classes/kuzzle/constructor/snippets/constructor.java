Options options = new Options();
options.setAutoResubscribe(false);

WebSocket ws = new WebSocket("kuzzle");
Kuzzle kuzzle = new Kuzzle(ws, options);
