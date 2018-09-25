try {
  kuzzle->collection->deleteSpecifications("nyc-open-data", "yellow-taxi");

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
