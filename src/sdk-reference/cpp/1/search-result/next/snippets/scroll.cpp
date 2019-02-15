try {
  for (size_t i = 0; i < 10; i++) {
    kuzzle->document->create("nyc-open-data", "yellow-taxi", "", R"({
      "category": "suv"
    })");
  }

  // Waits documents to be indexed
  kuzzle->index->refresh("nyc-open-data");

  kuzzleio::query_options options = {};
  options.scroll = "1m";
  options.size = 5;

  // Retrieve the first 5 documents
  std::shared_ptr<kuzzleio::SearchResult> results = kuzzle->document->search(
    "nyc-open-data",
    "yellow-taxi",
    R"({
      "query": {
        "match": {
          "category": "suv"
        }
      }
    })",
    options);

  // Retrieve the next 5 documents
  std::shared_ptr<kuzzleio::SearchResult> next_results = results->next();

  std::cout
    << "Successfully retrieved "
    << next_results->fetched()
    << " documents"
    << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
