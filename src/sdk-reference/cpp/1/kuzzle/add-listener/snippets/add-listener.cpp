kuzzleio::SharedEventListener listener = std::make_shared<EventListener>(
  [](const std::string &payload) {
    std::cout << payload << std::endl;
  });
kuzzleio::SharedEventListener other_listener = std::make_shared<EventListener>(
  [](const std::string &payload) {
    std::cerr << payload << std::endl;
  });

kuzzle
  ->addListener(KUZZLE_EVENT_CONNECTED, listener)
  ->addListener(KUZZLE_EVENT_CONNECTED, other_listener);

std::cout << "Listener successfully added" << std::endl;
