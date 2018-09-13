kuzzleio::EventListener listener = [](const std::string payload) {
  std::cout << payload << std::endl;
};
kuzzleio::EventListener other_listener = [](const std::string payload) {
  std::cerr << payload << std::endl;
};

kuzzle->addListener(CONNECTED, &listener)->addListener(CONNECTED, &other_listener);
