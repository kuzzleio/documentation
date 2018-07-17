try {
  kuzzle->index->delete_("nyc-open-data");
} catch (KuzzleException e) {
  std::cout << e.getMessage() << std::endl;
}
