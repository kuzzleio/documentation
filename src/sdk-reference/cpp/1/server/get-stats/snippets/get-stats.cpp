try {
  time_t start = std::time(0);
  time_t stop = std::time(0);

  kuzzle->server->getStats(start, stop);

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
