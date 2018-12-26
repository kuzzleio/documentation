#include <iostream>

#include "websocket.hpp"
#include "kuzzle.hpp"

int main(int argc, char * argv[]) {
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

    try {
        // Get server current date as UNIX timestamp.
        std::cout << "Server current timestamp: "
                  << kuzzle->server->now()
                  << std::endl;
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
