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

  kuzzleio::Kuzzle* kuzzle = new kuzzleio::Kuzzle(hostname);

  [snippet-code]

  std::cout << "Success" << std::endl;

  return 0;
}
