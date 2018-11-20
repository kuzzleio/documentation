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

  kuzzleio::query_options options = {};
  options.scroll = "1m";
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

  kuzzleio::SearchResult* next_page = response->next();

  std::cout << "Successfully retrieved " << next_page->fetched << " documents" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}