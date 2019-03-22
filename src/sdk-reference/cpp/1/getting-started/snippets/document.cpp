#include <iostream>
#include "kuzzle.hpp"
#include "websocket.hpp"

int main(int argc, char * argv[]) {
  // Instantiate a Kuzzle client with a WebSocket connection.
  // Replace "kuzzle" with your actual Kuzzle hostname (e.g. "localhost")
  kuzzleio::WebSocket *ws = new kuzzleio::WebSocket("kuzzle");
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

  // New document content
  std::string document = R"(
    {
      "name": "Sirkis",
      "birthday": "1959-06-22",
      "license": "B"
    }
  )";

  try {
    // Store the document in the "yellow-taxi" collection, itself in the
    // "nyc-open-data" data index
    std::string response = kuzzle->document->create(
        "nyc-open-data",
        "yellow-taxi",
        "document-id",
        document);
    std::cout << "Document created successfully: " << response << std::endl;
  }
  catch(kuzzleio::KuzzleException &e) {
      std::cerr << e.what() << std::endl;
      exit(1);
  }

  // Disconnect and free allocated resources
  kuzzle->disconnect();

  delete kuzzle;
  delete ws;

  return 0;
}
