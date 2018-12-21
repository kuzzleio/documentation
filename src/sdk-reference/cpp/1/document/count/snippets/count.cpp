try {
  int count = kuzzle->document->count("nyc-open-data", "yellow-taxi", R"({
    "query": {
      "match": {
        "license": "valid"
      }
    }
  })");

  std::cout << "Found " << count << " documents matching license:valid" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.what() << std::endl;
}
