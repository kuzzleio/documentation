try {
  kuzzle->index->delete_("nyc-open-data");
  std::cout << "index deleted" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
