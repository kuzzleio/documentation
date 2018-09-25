if (kuzzle->index->exists("nyc-open-data"))
  std::cout << "index exists" << std::endl;
else
  std::cerr << "index does not exist" << std::endl;
