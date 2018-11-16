try {
  for (int i = 0; i < 5; i++) {
    kuzzle->document->create("nyc-open-data", "yellow-taxi", "", R"({
      "category": "suv"
    })");
  }
  for (int i = 5; i < 15; i++) {
    kuzzle->document->create("nyc-open-data", "yellow-taxi", "", R"({
      "category": "limousine"
    })");
  }
  kuzzle->index->refresh("nyc-open-data");

  kuzzleio::query_options options;
  options.from = 0;
  options.size = 2;

  kuzzleio::SearchResult* response = kuzzle->document->search(
    "nyc-open-data",
    "yellow-taxi",
    R"({
      "query": {
        "match": {
          "category": "suv"
        }
      }
    })",
    &options);

  std::cout << "Successfully retrieved " << response->total << " documents" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
