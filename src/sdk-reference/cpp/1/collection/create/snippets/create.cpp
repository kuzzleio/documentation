try {
  std::string mapping = R"({
    "properties": {
      "license": { "type": "keyword" },
      "driver": {
        "properties": {
          "name": { "type": "keyword" },
          "curriculum": { "type": "text" }
        }
      }
    }
  })";

  kuzzle->collection->create("nyc-open-data", "yellow-taxi", mapping);

  std::cout << "Collection successfully created" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
