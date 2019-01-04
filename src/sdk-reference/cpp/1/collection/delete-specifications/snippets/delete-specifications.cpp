try {
  kuzzle->collection->deleteSpecifications("nyc-open-data", "yellow-taxi");

  std::cout << "Specifications successfully deleted" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
