#include <iostream>
#include <string>
#include <vector>

#include "kuzzle.hpp"
#include "websocket.hpp"

int main() {
  std::string hostname = "kuzzle";

  kuzzleio::WebSocket* ws = new kuzzleio::WebSocket(hostname);
  kuzzleio::Kuzzle* kuzzle = new kuzzleio::Kuzzle(ws);

  char* error = kuzzle->connect();
  if (error != nullptr) {
    std::cerr << "Unable to connect to " << hostname << ":7512\n" << error << std::endl;
    return 1;
  }

  try {
    [snippet-code]
  } catch (kuzzleio::KuzzleException &e) {
    std::cout << "Success" << std::endl;
  }
  return 0;
}
