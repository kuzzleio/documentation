kuzzleio::NotificationListener listener =
  [](const kuzzleio::notification_result *notification) {
    std::cout << "Document moved " << notification->scope << " from the scope" << std::endl;
  };

try {
  // Subscribe to notifications when document leaves the scope
  const char *filters = "{ \"range\": { \"age\": { \"lte\": 20 } } }";
  kuzzleio::room_options options;
  options.scope = "out";

  kuzzle->realtime->subscribe(
    "nyc-open-data",
    "yellow-taxi",
    filters,
    &listener,
    &options);

  const char *document = "{ \"name\": \"nina vkote\", \"age\": 19 }";

  // The document is in the scope
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "nina-vkote", document);

  // The document isn't in the scope anymore
  kuzzle->document->update(
    "nyc-open-data",
    "yellow-taxi",
    "nina-vkote",
    "{ \"age\": 42 }");
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
