kuzzleio::NotificationListener listener =
  [](const kuzzleio::notification_result *notification){};

try {
  std::string room_id = kuzzle->realtime->subscribe(
    "nyc-open-data",
    "yellow-taxi",
    "{}",
    &listener);

  kuzzle->realtime->unsubscribe(room_id);

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
