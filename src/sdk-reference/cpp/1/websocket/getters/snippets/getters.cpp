kuzzleio::WebSocket *protocol = new WebSocket("kuzzle");

unsigned int port = protocol->getPort();

bool ssl_connection = protocol->isSslConnection();
