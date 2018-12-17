options options;
options.auto_resubscribe = false;
string hostname = "kuzzle";

WebSocket* ws = new WebSocket(hostname);

Kuzzle *kuzzle = new Kuzzle(ws, options);
