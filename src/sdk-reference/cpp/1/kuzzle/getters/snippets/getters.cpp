kuzzleio::WebSocket *ws = new kuzzleio::WebSocket("kuzzle");
kuzzleio::Kuzzle *kuzzle = new kuzzleio::Kuzzle(ws);

std::string jwt_token = kuzzle->jwt();

kuzzleio::Protocol *protocol = kuzzle->getProtocol();

unsigned long max_size = kuzzle->queueMaxSize();

unsigned int queue_ttl = kuzzle->queueTTL();

unsigned long replay_interval = kuzzle->replayInterval();

unsigned long reconnection_delay = kuzzle->reconnectionDelay();

std::string volatile_data = kuzzle->volatiles();

bool auto_queue = kuzzle->autoQueue();

bool auto_reconnect = kuzzle->autoReconnect();

bool auto_replay = kuzzle->autoReplay();

bool auto_resubscribe = kuzzle->autoResubscribe();
