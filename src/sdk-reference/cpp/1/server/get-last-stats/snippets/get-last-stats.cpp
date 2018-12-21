try {
  std::string lastStats = kuzzle->server->getLastStats();
  std::cout << "Last Kuzzle Stats as JSON string: " << lastStats << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.what() << std::endl;
}
