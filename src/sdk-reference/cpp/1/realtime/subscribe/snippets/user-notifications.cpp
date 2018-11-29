kuzzleio::NotificationListener listener =
  [](const kuzzleio::notification_result *notification) {
    std::cout << notification->volatile << std::endl;
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

  // Instantiates a second kuzzle client: multiple subscriptions
  // made by the same user will not trigger "new user" notifications
  kuzzleio::WebSocket* ws2 = new kuzzleio::WebSocket("kuzzle");
  kuzzleio::Kuzzle* kuzzle2 = new kuzzleio::Kuzzle(ws2);
  kuzzle2->connect();

  // Set some volatile data
  kuzzleio::room_options options2;
  options2.volatiles = "{ \"username\": \"nina vkote\" }";

  // Subscribe to the same room with the second client
  kuzzle2->realtime->subscribe(
    "nyc-open-data",
    "yellow-taxi",
    filters,
    &listener,
    &options2);
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
