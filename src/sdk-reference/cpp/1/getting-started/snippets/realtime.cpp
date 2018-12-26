#include <iostream>

#include "websocket.hpp"
#include "kuzzle.hpp"

#define K_INDEX_NAME "nyc-open-data"
#define K_COLLECTION_NAME "yellow-taxi"

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

    // Starts an async listener
    kuzzleio::NotificationListener listener =
      [](const kuzzleio::notification_result *notification) {
        const char *id = notification->result->id;
        std::cout << "New created document notification: " << id << std::endl;
      };

    try {
      // Subscribes to notifications for drivers having a "B" driver license.
      const char *filters = R"(
        {
           "equals": {
             "license": "B"
           }
        }
      )";

      // Sends the subscription
      kuzzle->realtime->subscribe(K_INDEX_NAME, K_COLLECTION_NAME, filters, &listener);
      std::cout << "Successfully subscribed!" << std::endl;

      // Writes a new document. This triggers a notification
      // sent to our subscription.
      const char *document = R"(
        {
          "name": "John",
          "birthday": "1995-11-27",
          "license": "B"
        }
      )";

      kuzzle->document->create(K_INDEX_NAME, K_COLLECTION_NAME, "some-id", document);
    } catch (kuzzleio::KuzzleException &e) {
      std::cerr << e.what() << std::endl;
      kuzzle->disconnect();
      exit(1);
    }

    // Disconnects the SDK.
    kuzzle->disconnect();

    return 0;
}
