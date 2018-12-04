#include <iostream>
#include <string>
#include <vector>

#include "kuzzle.hpp"
#include "websocket.hpp"

int main() {
  std::string hostname = "kuzzle";

  kuzzleio::WebSocket* ws = new kuzzleio::WebSocket(hostname);
  kuzzleio::Kuzzle* kuzzle = new kuzzleio::Kuzzle(ws);

  [snippet-code]

  std::cout << "Success" << std::endl;

  return 0;
}
