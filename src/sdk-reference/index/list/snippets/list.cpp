std::vector<std::string> indexes = kuzzle->index->list();

std::cout << "Kuzzle contains " << indexes.size() << " indexes" << std::endl;
