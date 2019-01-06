#include <iostream>

#include "kuzzle.hpp"
#include "websocket.hpp"

#define K_INDEX_NAME "nyc-open-data"
#define K_COLLECTION_NAME "yellow-taxi"


int main(int argc, char * argv[]) {
    // Instanciate a Kuzzle client
    // with a WebSocket connection.
    // Replace "kuzzle" with
    // your Kuzzle hostname like "localhost"
    Kuzzle *kuzzle = new Kuzzle(new WebSocket("kuzzle"));

    try {
        // Connects to the server.
        kuzzle.connect();
        Console.WriteLine("Connected!");

        // Freshly installed Kuzzle servers are empty: we need to create
        // a new index.
        kuzzle.index.create(K_INDEX_NAME);
        std::cout << "Index "
                  << K_INDEX_NAME
                  << " created!"
                  << std::endl;

        // Creates a collection
        kuzzle.collection.create(K_INDEX_NAME, K_COLLECTION_NAME);
        std::cout << "Collection "
                  << K_COLLECTION_NAME
                  << " created!"
                  << std::endl;
    }
    catch(KuzzleException e) {
        Console.Error.WriteLine(e.Message());
        exit(1);
    }

    // Disconnects the SDK
    kuzzle.disconnect();

    return 0;
}

