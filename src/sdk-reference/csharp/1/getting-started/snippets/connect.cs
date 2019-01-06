#include <iostream>

#include "websocket.hpp"
#include "kuzzle.hpp"

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
    }
    catch(KuzzleException e) {
        Console.Error.WriteLine(e.Message());
        exit(1);
    }

    try {
        // Get server current date as UNIX timestamp.
        std::cout << "Server current timestamp: "
                  << kuzzle.server.now()
                  << std::endl;
    }
    catch(KuzzleException e) {
        Console.Error.WriteLine(e.Message());
        kuzzle.disconnect();
        exit(1);
    }

    // Disconnects the SDK.
    kuzzle.disconnect();

    return 0;
}
