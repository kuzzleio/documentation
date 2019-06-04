#include <iostream>
#include "kuzzle.hpp"
#include "websocket.hpp"

int main(int argc, char * argv[]) {
  // Instantiate a Kuzzle client with a WebSocket connection.
  // Replace "kuzzle" with your actual Kuzzle hostname (e.g. "localhost")
  kuzzleio::WebSocket *ws = new kuzzleio::WebSocket("kuzzle");
  kuzzleio::Kuzzle *kuzzle = new kuzzleio::Kuzzle(ws);

  try {
    // Connect to the server.
    kuzzle->connect();
    std::cout << "Connected!" << std::endl;

    // Freshly installed Kuzzle servers are empty: we first need to create
    // a index. The one used in this example is named "nyc-open-data"
    kuzzle->index->create("nyc-open-data");
    std::cout << "Index nyc-open-data created!" << std::endl;

    // Create a collection named "yellow-taxi" in our newly created index
    kuzzle->collection->create("nyc-open-data", "yellow-taxi");
    std::cout << "Collection yellow-taxi created!" << std::endl;
  }
  catch(kuzzleio::KuzzleException e) {
    std::cerr << e.what() << std::endl;
    exit(1);
  }

  // Disconnect and free allocated resources
  kuzzle->disconnect();

  delete kuzzle;
  delete ws;

  return 0;
}
