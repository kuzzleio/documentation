if (kuzzle->index->exists("nyc-open-data"))
  std::cout << "index exists" << std::endl;
else
  std::cout << "index does not exist" << std::endl;
