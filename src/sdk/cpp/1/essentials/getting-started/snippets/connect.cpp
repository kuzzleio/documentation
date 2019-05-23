#include <iostream>

#include "websocket.hpp"
#include "kuzzle.hpp"

int main(int argc, char * argv[]) {
  // Instantiate a Kuzzle client with a WebSocket connection.
  // Replace "kuzzle" with your actual Kuzzle hostname (e.g. "localhost")
  kuzzleio::WebSocket * ws = new kuzzleio::WebSocket("kuzzle");
  kuzzleio::Kuzzle *kuzzle = new kuzzleio::Kuzzle(ws);

  try {
    // Connect to the server.
    kuzzle->connect();
    std::cout << "Connected!" << std::endl;
  }
  catch(kuzzleio::KuzzleException &e) {
    std::cerr << e.what() << std::endl;
    exit(1);
  }

  try {
    // Get the server current date as a UNIX timestamp.
    std::cout << "Server current timestamp: "
    << kuzzle->server->now()
    << std::endl;
  }
  catch(kuzzleio::KuzzleException &e) {
    std::cerr << e.what() << std::endl;
    kuzzle->disconnect();
    exit(1);
  }

  // Disconnect and free resources
  kuzzle->disconnect();
  delete kuzzle;
  delete ws;

  return 0;
}
