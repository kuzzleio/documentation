try {
  bool autorefresh_flag = kuzzle->index->getAutoRefresh("nyc-open-data");

  if (autorefresh_flag) {
    std::cout << "Autorefresh is enabled" << std::endl;
  } else {
    std::cerr << "Autorefresh is disabled" << std::endl;
  }
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
