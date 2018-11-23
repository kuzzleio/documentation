#include <unistd.h>

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

int main() {
  std::string hostname = "kuzzle";

  kuzzleio::Kuzzle* kuzzle = new kuzzleio::Kuzzle(hostname);

  char* error = kuzzle->connect();
  if (error != nullptr) {
    std::cerr << "Unable to connect to " << hostname << ":7512\n" << error << std::endl;
    return 1;
  }

  [snippet-code]
  sleep(1);
  return 0;
}
