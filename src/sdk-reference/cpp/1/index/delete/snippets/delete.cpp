try {
  kuzzle->index->delete_("nyc-open-data");

  std::cout << "Index successfully deleted" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
