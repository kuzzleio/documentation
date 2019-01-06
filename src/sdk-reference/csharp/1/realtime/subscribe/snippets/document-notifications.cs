NotificationListener listener =
  [](const notification_result *notification) {
    string id = notification.result.id;

    if (notification.scope == string("in")) {
      Console.WriteLine("Document " + id + " enter the scope");
    } else {
      Console.WriteLine("Document " + id + " leave the scope");
    }
  };

try {
  // Subscribe to notifications for documents containing a 'name' property
  string filters = @"{ ""exists"": ""name"" }";
  kuzzle.realtime.subscribe("nyc-open-data", "yellow-taxi", filters, listener);

  // Create a document matching the provided filters
  kuzzle.document.create(
    "nyc-open-data",
    "yellow-taxi",
    "nina-vkote",
    @"{ ""name"": ""nina vkote"", ""age"": 19 }");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
