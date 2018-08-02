try {
  kuzzle->index->delete_("nyc-open-data");
} catch (KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
