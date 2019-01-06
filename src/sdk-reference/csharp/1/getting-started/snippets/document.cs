#include <iostream>

#include "kuzzle.hpp"
#include "websocket.hpp"

#define K_INDEX_NAME "nyc-open-data"
#define K_COLLECTION_NAME "yellow-taxi"


int main(int argc, char * argv[]) {
    string res;
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

    // New document content
    string document = @"
      {
        ""name"": ""Sirkis"",
        ""birthday"": ""1959-06-22"",
        ""license"": ""B""
      }
    ";

    try {
        // Stores the document in the "yellow-taxi" collection.
        res = kuzzle.document.create(K_INDEX_NAME, K_COLLECTION_NAME, "some-id", document);
        Console.WriteLine("Document created successfuly: " + res);
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
