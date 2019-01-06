NotificationListener listener =
  [](const notification_result *notification) {
    Console.WriteLine("Document moved " + notification.scope + " from the scope");
  };

try {
  // Subscribes to notifications when document leaves the scope
  string filters = @"{ ""range"": { ""age"": { ""lte"": 20 } } }";
  room_options options = new options();
  options.scope = "out";

  kuzzle.realtime.subscribe(
    "nyc-open-data",
    "yellow-taxi",
    filters,
    listener,
    options);

  // Creates a document who is in the scope
  kuzzle.document.create(
    "nyc-open-data",
    "yellow-taxi",
    "nina-vkote",
    @"{ ""name"": ""nina vkote"", ""age"": 19 }");

  // Update the document so he isn't in the scope anymore
  // we shall receive a notification
  kuzzle.document.update(
    "nyc-open-data",
    "yellow-taxi",
    "nina-vkote",
    @"{ ""age"": 42 }");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
