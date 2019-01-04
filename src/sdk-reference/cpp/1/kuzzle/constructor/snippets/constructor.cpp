std::string hostname = "kuzzle";
kuzzleio::WebSocket* ws = new kuzzleio::WebSocket(hostname);

kuzzleio::options options;
options.auto_resubscribe = false;

kuzzleio::Kuzzle *kuzzle = new kuzzleio::Kuzzle(ws, options);

std::cout << "New SDK instance successfully created" << std::endl;
