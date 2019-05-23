try {
  kuzzle->index->refresh("nyc-open-data");

  std::cout << "Index successfully refreshed" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
