try {
  ctx->kuzzle->index->create("nyc-open-data")
  std::cout << "index created" << std::endl;
} catch (KuzzleException e) {
  std::cout << e.getMessage() << std::endl;
}
