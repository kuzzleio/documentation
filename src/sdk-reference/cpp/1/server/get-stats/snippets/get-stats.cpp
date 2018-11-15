try {
  time_t start = std::time(0);
  time_t stop = std::time(0);

  std::string stats = kuzzle->server->getStats(start, stop);

  std::cout << "Kuzzle Stats as JSON string: " << stats << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
