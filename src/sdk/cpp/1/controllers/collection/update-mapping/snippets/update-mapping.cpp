try {
  std::string mapping = R"({
    "properties": {
      "plate": { "type": "keyword" }
    }
  })";

  kuzzle->collection->updateMapping("nyc-open-data", "yellow-taxi", mapping);

  std::cout << "Mapping successfully updated" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
