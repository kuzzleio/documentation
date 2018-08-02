std::vector<std::string> indexes = kuzzle->index->list();

std::cerr << "Kuzzle contains " << indexes.size() << " indexes" << std::endl;
