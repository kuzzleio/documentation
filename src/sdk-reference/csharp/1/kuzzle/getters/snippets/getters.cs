Kuzzle *kuzzle = new Kuzzle(
  new WebSocket("kuzzle"));

Protocol *protocol = kuzzle.getProtocol();

string jwt_token = kuzzle.getJwt();

string volatile_data = kuzzle.getVolatile();

std::map<int, EventListener*> listeners = kuzzle.getListeners();
