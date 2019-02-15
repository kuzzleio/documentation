std::string hostname = "kuzzle";
kuzzleio::WebSocket* ws = new kuzzleio::WebSocket(hostname);

kuzzleio::Options options;
options.autoResubscribe(false);

kuzzleio::Kuzzle *kuzzle = new kuzzleio::Kuzzle(ws, options);

std::cout << "New SDK instance successfully created" << std::endl;
