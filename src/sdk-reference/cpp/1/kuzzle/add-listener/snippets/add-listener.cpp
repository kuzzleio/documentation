{
  using kuzzleio::SharedEventListener;
  using kuzzleio::EventListener;

  SharedEventListener listener = std::make_shared<EventListener>(
    [](const std::string &payload) {
      std::cout << payload << std::endl;
    });

  SharedEventListener other_listener = std::make_shared<EventListener>(
    [](const std::string &payload) {
      std::cerr << payload << std::endl;
    });

  kuzzle
    ->addListener(kuzzleio::KUZZLE_EVENT_CONNECTED, listener)
    ->addListener(kuzzleio::KUZZLE_EVENT_CONNECTED, other_listener);
}

std::cout << "Listener successfully added" << std::endl;
