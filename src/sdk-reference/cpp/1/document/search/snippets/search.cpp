try {
  int i;

  for (i = 0; i < 5; i++) {
    kuzzle->document->create("nyc-open-data", "yellow-taxi", "", R"({
      "category": "suv"
    })");
  }
  for (i = 5; i < 15; i++) {
    kuzzle->document->create("nyc-open-data", "yellow-taxi", "", R"({
      "category": "limousine"
    })");
  }
  kuzzle->index->refresh("nyc-open-data");

  kuzzleio::search_result* response = kuzzle->document->search("nyc-open-data", "yellow-taxi", R"(
    {
      "query": {
        "match": {
          "category": "suv"
        }
      }
    }
  )");

  std::cout << "Successfully retrieved " << response->total << " documents" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
