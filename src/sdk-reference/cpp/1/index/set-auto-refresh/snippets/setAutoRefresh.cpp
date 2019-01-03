try {
  kuzzle->index->setAutoRefresh("nyc-open-data", true);

  std::cout << "Autorefresh is now enabled on index" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
