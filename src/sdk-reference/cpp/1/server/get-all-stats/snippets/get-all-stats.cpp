try {
  std::string allStats = kuzzle->server->getAllStats();
  std::cout << "All Kuzzle Stats as JSON string: " << allStats << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
