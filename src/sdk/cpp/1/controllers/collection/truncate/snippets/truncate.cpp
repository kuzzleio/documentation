try {
  kuzzle->collection->truncate("nyc-open-data", "yellow-taxi");

  std::cout << "Collection successfully truncated" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
