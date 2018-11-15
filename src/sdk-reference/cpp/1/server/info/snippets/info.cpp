try {
  std::string info = kuzzle->server->info();
  std::cout << "Kuzzle Server information as JSON string: " << info << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
