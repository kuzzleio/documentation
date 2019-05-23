try {
  bool exists = kuzzle->collection->exists("nyc-open-data", "green-taxi");

  if (exists) {
    std::cout << "Collection green-taxi exists" << std::endl;
  }
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
