try {
  int count;
  count = kuzzle->document->count("nyc-open-data", "yellow-taxi", R"({
    "query": {
      "match": {
        "license": "valid"
      }
    }
  })");

  std::cout << "Found " << count << " documents matching licence:valid" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
