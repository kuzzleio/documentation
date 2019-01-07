try {
  kuzzleio::SearchResult* results = kuzzle->document->search(
    "nyc-open-data",
    "yellow-taxi",
    R"({
      "query": {
        "match": {
          "category": "suv"
        }
      }
    })");

  size_t total_documents = results->total();
  size_t fetched_documents = results->fetched();
  std::string aggregations = results->aggregations();
  std::string hits = results->hits();
  std::string scroll_id = results->scroll_id();

  std::cout << "Snippet success" << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
