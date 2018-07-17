try {
  kuzzle->index->refreshInternal();

  std::cout << "Internal index successfully refreshed" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cout << e.getMessage() << std::endl;
}
