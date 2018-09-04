#include <iostream>
#include <string>
#include <vector>

#include "auth.hpp"
#include "collection.hpp"
#include "document.hpp"
#include "index.hpp"
#include "realtime.hpp"
#include "kuzzle.hpp"
#include "listeners.hpp"

class MyListener : public kuzzleio::EventListener {
 public:
    void trigger(const std::string& jsonResponse) {
      std::cout << jsonResponse << std::endl;
    }
};

int main() {
  std::string hostname = "kuzzle";

  kuzzleio::options kuzzle_options = KUZZLE_OPTIONS_DEFAULT;

  kuzzleio::Kuzzle* kuzzle = new kuzzleio::Kuzzle(hostname, &kuzzle_options);

  [snippet-code]

  std::cout << "Success" << std::endl;

  return 0;
}
