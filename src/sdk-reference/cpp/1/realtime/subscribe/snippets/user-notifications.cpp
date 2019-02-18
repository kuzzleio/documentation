kuzzleio::NotificationListener listener =
  [](const std::shared_ptr<kuzzleio::notification_result> &notification) {
    std::cout << "Currently " << notification->result->count << " users in the room" << std::endl;

    std::cout << notification->volatiles << std::endl;
    // "{ "username": "nina vkote" }"
  };

try {
  // Subscription to notifications and notifications when user join or leave
  const char *filters = R"({ "exists": "name" })";
  kuzzleio::room_options options;
  options.users = "all";

  kuzzle->realtime->subscribe(
    "nyc-open-data",
    "yellow-taxi",
    filters,
    &listener,
    options);

  // Instantiates a second kuzzle client:
  //  multiple subscriptions made by the same user
  //  will not trigger "new user" notifications
  kuzzleio::WebSocket* ws2 = new kuzzleio::WebSocket("kuzzle");
  kuzzleio::Kuzzle* kuzzle2 = new kuzzleio::Kuzzle(ws2);
  kuzzle2->connect();

  // Set some volatile data
  kuzzleio::room_options options2;
  options2.volatiles = R"({ "username": "nina vkote" })";

  // Subscribe to the same room with the second client
  kuzzle2->realtime->subscribe(
    "nyc-open-data",
    "yellow-taxi",
    filters,
    &listener,
    options2);
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
