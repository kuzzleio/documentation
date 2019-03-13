kuzzleio::WebSocket *protocol = new kuzzleio::WebSocket("kuzzle");

unsigned int port = protocol->getPort();

bool ssl_connection = protocol->isSslConnection();
