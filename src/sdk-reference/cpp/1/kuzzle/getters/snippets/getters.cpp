auto *ws = new kuzzleio::WebSocket("kuzzle");
auto *kuzzle = new kuzzleio::Kuzzle(ws);

std::string jwt_token = kuzzle->jwt();

auto *protocol = kuzzle->getProtocol();

uint32_t max_size = kuzzle->queueMaxSize();

uint16_t queue_ttl = kuzzle->queueTTL();

uint32_t replay_interval = kuzzle->replayInterval();

uint32_t reconnection_delay = kuzzle->reconnectionDelay();

std::string volatile_data = kuzzle->volatiles();

bool auto_queue = kuzzle->autoQueue();

bool auto_reconnect = kuzzle->autoReconnect();

bool auto_replay = kuzzle->autoReplay();

bool auto_resubscribe = kuzzle->autoResubscribe();
