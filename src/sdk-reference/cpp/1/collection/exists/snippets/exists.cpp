try {
  if (kuzzle->collection->exists("nyc-open-data", "green-taxi")) {
    std::cout << "Success" << std::endl;
  }
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
