#include <iostream>

#include "kuzzle.hpp"
#include "websocket.hpp"

using kuzzleio::Kuzzle;

#define K_INDEX_NAME "nyc-open-data"
#define K_COLLECTION_NAME "yellow-taxi"


int main(int argc, char * argv[]) {
    // Instanciate a Kuzzle client
    // with a WebSocket connection.
    // Replace "kuzzle" with
    // your Kuzzle hostname like "localhost"
    Kuzzle *k = new Kuzzle(new WebSocket("kuzzle"));

    try {

        // Connects to the server.
        k->connect();
        std::cout << "Connected!" << std::endl;

        // Freshly installed Kuzzle servers are empty: we need to create
        // a new index.
        k->index->create(K_INDEX_NAME);
        std::cout << "Index "
                  << K_INDEX_NAME
                  << " created!"
                  << std::endl;

        // Creates a collection
        k->collection->create(K_INDEX_NAME, K_COLLECTION_NAME);
        std::cout << "Collection "
                  << K_COLLECTION_NAME
                  << " created!"
                  << std::endl;
    }
    catch(KuzzleException e) {
        std::cerr << e.getMessage() << std::endl;
        exit(1);
    }

    // Disconnects the SDK
    k->disconnect();

    return 0;
}

