kuzzleio::Kuzzle* kuzzle = new kuzzleio::Kuzzle(
  new kuzzleio::WebSocket("kuzzle"));

kuzzleio::Protocol* protocol = kuzzle->getProtocol();

std::string jwt_token = kuzzle->getJwt();

std::string volatile_data = kuzzle->getVolatile();

std::map<int, EventListener*> listeners = kuzzle->getListeners();
