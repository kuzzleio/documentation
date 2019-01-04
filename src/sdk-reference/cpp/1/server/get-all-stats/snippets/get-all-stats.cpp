try {
  std::string statistics = kuzzle->server->getAllStats();

  std::cout << statistics << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
