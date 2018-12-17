NotificationListener listener =
  [](const notification_result *notification) {
    const char *id = notification.result.id;

    if (notification.scope == string("in")) {
      Console.WriteLine("Document " + id + " enter the scope");
    } else {
      Console.WriteLine("Document " + id + " leave the scope");
    }
  };

try {
  // Subscribe to notifications for documents containing a 'name' property
  const char *filters = "{ \"exists\": \"name\" }";
  kuzzle.realtime.subscribe("nyc-open-data", "yellow-taxi", filters, listener);

  const char *document = "{ \"name\": \"nina vkote\", \"age\": 19 }";
  kuzzle.document.create("nyc-open-data", "yellow-taxi", "nina-vkote", document);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
