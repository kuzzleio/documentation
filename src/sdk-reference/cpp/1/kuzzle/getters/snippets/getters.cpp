kuzzleio::Kuzzle *kuzzle = new kuzzleio::Kuzzle(
  new kuzzleio::WebSocket("kuzzle"));

std::string jwt_token = kuzzle->getJwt();

kuzzleio::Protocol *protocol = kuzzle->getProtocol();

int max_size = kuzzle->getQueueMaxSize();

int queue_ttl = kuzzle->getQueueTTL();

int replay_interval = kuzzle->getReplayInterval();

int reconnection_delay = kuzzle->getReconnectionDelay();

std::string volatile_data = kuzzle->getVolatile();

bool auto_queue = kuzzle->isAutoQueue();

bool auto_reconnect = kuzzle->isAutoReconnect();

bool auto_replay = kuzzle->isAutoReplay();

bool auto_resubscribe = kuzzle->isAutoResubscribe();