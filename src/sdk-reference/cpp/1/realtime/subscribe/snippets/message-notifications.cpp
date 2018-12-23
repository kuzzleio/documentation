kuzzleio::NotificationListener listener =
  [](const kuzzleio::notification_result *notification) {
    std::cout << "Message notification received" << std::endl;

    std::cout << notification->result->content << std::endl;
    // { "metAt": "Insane", "hello": "world" }
  };

try {
  // Subscribes to notifications for documents
  kuzzle->realtime->subscribe("i-dont-exist", "in-database", "{}", &listener);

  std::string message = R"({ "metAt": "Insane", "hello": "world" })";
  kuzzle->realtime->publish("i-dont-exist", "in-database", message);
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
