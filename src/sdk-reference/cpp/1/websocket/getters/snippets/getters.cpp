kuzzleio::WebSocket *protocol = new WebSocket("kuzzle");

bool auto_reconnects = protocol->isAutoReconnect();

bool auto_resubscribes = protocol->isAutoResubscribe();

unsigned int port = protocol->getPort();

uint64_t reconnection_delay = protocol->getReconnectionDelay();

bool ssl_connection = protocol->isSslConnection();
