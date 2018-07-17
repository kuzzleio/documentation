#include <iostream>
#include <string>
#include <vector>

#include "auth.hpp"
#include "collection.hpp"
#include "document.hpp"
#include "index.hpp"
#include "realtime.hpp"
#include "kuzzle.hpp"

int main() {
  std::string hostname = "kuzzle";

  options kuzzle_options = KUZZLE_OPTIONS_DEFAULT;
  kuzzle_options.connect = MANUAL;

  kuzzleio::Kuzzle* kuzzle = new kuzzleio::Kuzzle(hostname, &kuzzle_options);

  char* error = kuzzle->connect();
  if (error != nullptr) {
    std::cerr << "Unable to connect to " << hostname << ":7512\n" << error << std::endl;
    return 1;
  }

  [snippet-code]

  return 0;
}
