try {
  kuzzle->index->create("nyc-open-data");*
  std::cout << "index created" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
