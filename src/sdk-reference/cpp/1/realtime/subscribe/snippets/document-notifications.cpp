kuzzleio::NotificationListener listener =
  [](const kuzzleio::notification_result *notification) {
    std::string id = notification->result->id;

    if (notification->scope == std::string("in")) {
      std::cout << "Document " << id << " enter the scope" << std::endl;
    } else {
      std::cout << "Document " << id << " leave the scope" << std::endl;
    }
  };

try {
  // Subscribe to notifications for documents containing a 'name' property
  std::string filters = R"({ "exists": "name" })";
  kuzzle->realtime->subscribe("nyc-open-data", "yellow-taxi", filters, &listener);

  // Create a document matching the provided filters
  kuzzle->document->create(
    "nyc-open-data",
    "yellow-taxi",
    "nina-vkote",
    R"({ "name": "nina vkote", "age": 19 })");
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
