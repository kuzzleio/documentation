#include <unistd.h>

#include <sstream>
#include <iostream>
#include <string>
#include <ctime>
#include <vector>

#include "auth.hpp"
#include "collection.hpp"
#include "document.hpp"
#include "server.hpp"
#include "index.hpp"
#include "realtime.hpp"
#include "kuzzle.hpp"

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

  kuzzleio::Kuzzle* kuzzle = new kuzzleio::Kuzzle(hostname);

  char* error = kuzzle->connect();
  if (error != nullptr) {
    std::cerr << "Unable to connect to " << hostname << ":7512\n" << error << std::endl;
    return 1;
  }

  std::streambuf *cout_original = std::cout.rdbuf();
  std::ostringstream cout_copy;

  std::cout.rdbuf(cout_copy.rdbuf());

  [snippet-code]

  while (is_empty(&cout_copy)) {
    std::cerr << "HEHEH" << std::endl;
    usleep(200);
  }

  std::cout.rdbuf(cout_original);
  std::cout << cout_copy.str();

  return 0;
}
