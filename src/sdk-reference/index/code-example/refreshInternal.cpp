try {
  kuzzle->index->refreshInternal();

  std::cout << "Internal index successfully refreshed" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
