kuzzleio::NotificationListener listener =
  [](const kuzzleio::notification_result *notification){};

try {
  std::string room_id = kuzzle->realtime->subscribe(
    "nyc-open-data",
    "yellow-taxi",
    "{}",
    &listener);

  int count = kuzzle->realtime->count(room_id);

  std::cout << "Currently " << count << " active subscription" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}