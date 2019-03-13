#include <unistd.h>
#include <iostream>
#include <memory>
#include "websocket.hpp"
#include "kuzzle.hpp"

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

  // Create a lambda that will be invoked for each real-time notification
  // received
  kuzzleio::NotificationListener listener =
    [](const std::shared_ptr<kuzzleio::notification_result> &notification) {
      const char *id = notification->result->id;
      std::cout << "New created document notification: " << id << std::endl;
    };

  try {
    // Subscribe to notifications for drivers having a "B" driver license.
    const char *filters = R"(
      {
         "equals": {
           "license": "B"
         }
      }
    )";

    kuzzle->realtime->subscribe(
        "nyc-open-data",
        "yellow-taxi",
        filters,
        &listener);
    std::cout << "Successfully subscribed!" << std::endl;

    // Write a new document. This triggers a notification
    // sent to our subscription.
    const char *document = R"(
      {
        "name": "John",
        "birthday": "1995-11-27",
        "license": "B"
      }
    )";

    kuzzle->document->create(
        "nyc-open-data",
        "yellow-taxi",
        "some-id",
        document);
  } catch (kuzzleio::KuzzleException &e) {
    std::cerr << e.what() << std::endl;
    kuzzle->disconnect();
    exit(1);
  }

  // Wait for the notification to be received
  sleep(2);

  // Disconnect and free allocated resources
  kuzzle->disconnect();

  delete kuzzle;
  delete ws;

  return 0;
}
