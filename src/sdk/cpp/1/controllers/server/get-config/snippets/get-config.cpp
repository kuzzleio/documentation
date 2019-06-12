try {
  std::string configuration = kuzzle->server->getConfig();

  std::cout << configuration << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
