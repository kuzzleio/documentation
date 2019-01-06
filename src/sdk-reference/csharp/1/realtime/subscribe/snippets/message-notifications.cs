NotificationListener listener =
  [](const notification_result *notification) {
    Console.WriteLine("Message notification received");

    Console.WriteLine(notification.result.content);
    // { "metAt": "Insane", "hello": "world" }
  };

try {
  // Subscribes to notifications for documents
  kuzzle.realtime.subscribe("i-dont-exist", "in-database", "{}", listener);

  string message = @"{ ""metAt"": ""Insane"", ""hello"": ""world"" }";
  kuzzle.realtime.publish("i-dont-exist", "in-database", message);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
