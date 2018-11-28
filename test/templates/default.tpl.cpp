#include <unistd.h>
#include <iostream>
#include <string>
#include <ctime>
#include <vector>

#include "kuzzle.hpp"
#include "websocket.hpp"

int main() {
  std::string hostname = "kuzzle";

  kuzzleio::WebSocket* ws = new kuzzleio::WebSocket(hostname);
  kuzzleio::Kuzzle* kuzzle = new kuzzleio::Kuzzle(ws);

  char* error = kuzzle->connect();
  if (error != nullptr) {
    std::cerr << "Unable to connect to " << hostname << ":" << kuzzle->getProtocol()->getPort() << std::endl << error << std::endl;
    return 1;
  }

  [snippet-code]
  return 0;
}
