kuzzleio::NotificationListener listener =
  [](const kuzzleio::notification_result *notification) {
    std::cout << notification->volatiles << std::endl;
    // "{ "username": "nina vkote" }"
    std::cout << "Currently " << notification->result->count << " users in the room" << std::endl;
  };

try {
  // Subscribe to notifications when document leaves the scope
  const char *filters = "{ \"exists\": \"name\" }";
  kuzzleio::room_options options;
  options.users = "all";

  kuzzle->realtime->subscribe(
    "nyc-open-data",
    "yellow-taxi",
    filters,
    &listener,
    &options);

  // instantiate a second kuzzle client because
  // the same sdk instance does not receive his own notifications
  kuzzleio::options fuzzle_options;
  kuzzleio::Kuzzle* fuzzle = new kuzzleio::Kuzzle("kuzzle", &fuzzle_options);
  fuzzle->connect();

  // Set some volatile data
  fuzzle->setVolatile("{ \"username\": \"nina vkote\" }");

  // Subscribe to the same room with the second client
  fuzzle->realtime->subscribe("nyc-open-data", "yellow-taxi", filters, &listener);
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
