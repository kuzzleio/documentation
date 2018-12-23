try {
  std::string last_statistics = kuzzle->server->getLastStats();

  std::cout << last_statistics << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
