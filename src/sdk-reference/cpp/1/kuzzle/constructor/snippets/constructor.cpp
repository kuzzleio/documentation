kuzzleio::options options;
options.auto_resubscribe = false;
std::string hostname = "kuzzle";

kuzzleio::WebSocket* ws = new kuzzleio::WebSocket(hostname);

kuzzleio::Kuzzle *kuzzle = new kuzzleio::Kuzzle(ws, &options);
