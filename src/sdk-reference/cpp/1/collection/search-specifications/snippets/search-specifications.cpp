try {
  kuzzleio::query_options options;
  options.from = 0;
  options.size = 2;

  kuzzleio::SearchResult* response = kuzzle->collection->searchSpecifications(R"({
    "query": {
      "match_all": {}
    }
  })", &options);

  std::cout << "Successfully retrieved " << response->fetched << " specifications" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
