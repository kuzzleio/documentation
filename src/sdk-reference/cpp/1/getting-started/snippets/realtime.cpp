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

  // For the sake of this example only: a simple toggle that is set
  // to true once a notification is received. This allows us to wait
  // before exiting the program
  bool notified = false;

  // Create a lambda that will be invoked for each real-time notification
  // received
  kuzzleio::NotificationListener listener =
    [&](const std::shared_ptr<kuzzleio::notification_result> &notification) {
      std::string id = notification->result->id;
      std::cout << "New created document notification: " << id << std::endl;
      notified = true;
    };

  try {
    // Subscribe to notifications for drivers having a "B" driver license.
    std::string filters = R"(
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
    std::string document = R"(
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

  // Waiting for a notification before exiting
  for (unsigned i = 0; i < 10 && !notified; ++i) {
    sleep(2);
  }

  // Disconnect and free allocated resources, allowing the
  // program to exit at the first notification received
  kuzzle->disconnect();

  delete kuzzle;
  delete ws;

  return 0;
}
