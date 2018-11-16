try {
  std::string config = kuzzle->server->getConfig();

  std::cout << "Kuzzle Server configuration as JSON string: " << config << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
