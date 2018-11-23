kuzzleio::NotificationListener listener =
  [](const kuzzleio::notification_result *notification) {
    const char *id = notification->result->id;

    if (notification->scope == std::string("in")) {
      std::cout << "Document " << id << " enter the scope" << std::endl;
    } else {
      std::cout << "Document " << id << " leave the scope" << std::endl;
    }
  };

try {
  // Subscribe to notifications for documents containing a 'name' property
  const char *filters = "{ \"exists\": \"name\" }";
  kuzzle->realtime->subscribe("nyc-open-data", "yellow-taxi", filters, &listener);

  const char *document = "{ \"name\": \"nina vkote\", \"age\": 19 }";
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "nina-vkote", document);

  sleep(1);
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
