try {
  kuzzle->index->create("nyc-open-data");

  std::cout << "Index successfully created" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
