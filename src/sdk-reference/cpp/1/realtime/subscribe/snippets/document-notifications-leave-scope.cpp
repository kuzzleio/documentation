kuzzleio::NotificationListener listener =
  [](std::shared_ptr<kuzzleio::notification_result> notification) {
    std::cout << "Document moved " << notification->scope << " from the scope" << std::endl;
  };

try {
  // Subscribes to notifications when document leaves the scope
  std::string filters = R"({ "range": { "age": { "lte": 20 } } })";
  kuzzleio::room_options options;
  options.scope = "out";

  kuzzle->realtime->subscribe(
    "nyc-open-data",
    "yellow-taxi",
    filters,
    &listener,
    options);

  // Creates a document who is in the scope
  kuzzle->document->create(
    "nyc-open-data",
    "yellow-taxi",
    "nina-vkote",
    R"({ "name": "nina vkote", "age": 19 })");

  // Update the document so he isn't in the scope anymore
  // we shall receive a notification
  kuzzle->document->update(
    "nyc-open-data",
    "yellow-taxi",
    "nina-vkote",
    R"({ "age": 42 })");
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
