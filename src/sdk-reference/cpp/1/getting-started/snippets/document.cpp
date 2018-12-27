#include <iostream>

#include "kuzzle.hpp"
#include "websocket.hpp"

#define K_INDEX_NAME "nyc-open-data"
#define K_COLLECTION_NAME "yellow-taxi"


int main(int argc, char * argv[]) {
    std::string res;
    // Instanciate a Kuzzle client
    // with a WebSocket connection.
    // Replace "kuzzle" with
    // your Kuzzle hostname like "localhost"
    Kuzzle *kuzzle = new kuzzleio::Kuzzle(new kuzzleio::WebSocket("kuzzle"));
    try {
        // Connects to the server.
        kuzzle->connect();
        std::cout << "Connected!" << std::endl;
    }
    catch(KuzzleException &e) {
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
        // Stores the document in the "yellow-taxi" collection.
        res = kuzzle->document->create(K_INDEX_NAME, K_COLLECTION_NAME, "some-id", document);
        std::cout << "Document created successfuly: " << res << std::endl;
    }
    catch(KuzzleException &e) {
        std::cerr << e.what() << std::endl;
        kuzzle->disconnect();
        exit(1);
    }

    // Disconnects the SDK.
    kuzzle->disconnect();

    return 0;
}
