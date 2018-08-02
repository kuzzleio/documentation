if (kuzzle->index->getAutoRefresh("nyc-open-data"))
  std::cout << "autorefresh is true" << std::endl;
else
  std::cerr << "autorefresh is false" << std::endl;
