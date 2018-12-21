kuzzleio::NotificationListener listener =
  [](const kuzzleio::notification_result *notification) {
    std::cout << "Message notification received" << std::endl;
  };

try {
  // Subscribe to notifications for documents containing a 'name' property
  kuzzle->realtime->subscribe("i-dont-exist", "in-database", "{}", &listener);

  std::string message = "{ \"metAt\": \"Insane\", \"hello\": \"world\" }";
  kuzzle->realtime->publish("i-dont-exist", "in-database", message);
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
