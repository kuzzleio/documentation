#include <unistd.h>

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

    // Starts an async listener
    NotificationListener listener =
      [](const notification_result *notification) {
        const char *id = notification.result.id;
        Console.WriteLine("New created document notification: " + id);
      };

    try {
      // Subscribes to notifications for drivers having a "B" driver license.
      const char *filters = @"
        {
           ""equals"": {
             ""license"": ""B""
           }
        }
      ";

      // Sends the subscription
      kuzzle.realtime.subscribe(K_INDEX_NAME, K_COLLECTION_NAME, filters, listener);
      Console.WriteLine("Successfully subscribed!");

      // Writes a new document. This triggers a notification
      // sent to our subscription.
      const char *document = @"
        {
          ""name"": ""John"",
          ""birthday"": ""1995-11-27"",
          ""license"": ""B""
        }
      ";

      kuzzle.document.create(K_INDEX_NAME, K_COLLECTION_NAME, "some-id", document);
      // Wait for notification
      sleep(2);
    } catch (KuzzleException e) {
      Console.Error.WriteLine(e.Message());
      kuzzle.disconnect();
      exit(1);
    }

    // Disconnects the SDK.
    kuzzle.disconnect();

    return 0;
}
