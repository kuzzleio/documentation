kuzzleio::EventListener listener = [](const std::string &payload) {
  std::cout << payload << std::endl;
};
kuzzleio::EventListener other_listener = [](const std::string &payload) {
  std::cerr << payload << std::endl;
};

kuzzle->addListener(EVENT_CONNECTED, &listener)->addListener(EVENT_CONNECTED, &other_listener);

std::cout << "Listener successfully added" << std::endl;
