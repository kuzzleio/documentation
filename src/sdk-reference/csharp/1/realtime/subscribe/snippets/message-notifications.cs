NotificationListener listener =
  [](const notification_result *notification) {
    Console.WriteLine("Message notification received");
  };

try {
  // Subscribe to notifications for documents containing a 'name' property
  kuzzle.realtime.subscribe("i-dont-exist", "in-database", "{}", listener);

  string message = "{ \"metAt\": \"Insane\", \"hello\": \"world\" }";
  kuzzle.realtime.publish("i-dont-exist", "in-database", message);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
