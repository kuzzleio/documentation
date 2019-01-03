try {
  bool exists = kuzzle->index->exists("nyc-open-data");

  if (exists) {
    std::cout << "Index exists in Kuzzle" << std::endl;
  }
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
