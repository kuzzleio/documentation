#include <unistd.h>
#include <sstream>
#include <iostream>
#include <string>
#include <ctime>
#include <vector>

#include "kuzzle.hpp"
#include "websocket.hpp"

bool is_empty(std::ostringstream *stream) {
  bool empty;
  std::streampos position = stream->tellp();

  stream->seekp(0, std::ios_base::end);
  empty = stream->tellp() == 0;
  stream->seekp(position);

  return empty;
}

int main() {
  std::string hostname = "kuzzle";

  kuzzleio::WebSocket* ws = new kuzzleio::WebSocket(hostname);
  kuzzleio::Kuzzle* kuzzle = new kuzzleio::Kuzzle(ws);

  try {
    kuzzle->connect();
  } catch (kuzzleio::KuzzleException e) {
    std::cerr << "Unable to connect to " << hostname << ":" << kuzzle->getProtocol()->getPort() << std::endl << e.what() << std::endl;
    return 1;
  }

  std::streambuf *cout_original = std::cout.rdbuf();
  std::ostringstream cout_copy;

  std::cout.rdbuf(cout_copy.rdbuf());

  [snippet-code]

  for (size_t i = 20; i > 0 && is_empty(&cout_copy); --i) {
    usleep(200);
  }

  std::cout.rdbuf(cout_original);
  std::cout << cout_copy.str();

  return 0;
}
