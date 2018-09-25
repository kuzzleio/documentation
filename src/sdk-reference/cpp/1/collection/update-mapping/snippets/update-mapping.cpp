try {
  std::string mapping = "{\"properties\": {\"plate\": {\"type\": \"keyword\"}}}";

  kuzzle->collection->updateMapping("nyc-open-data", "yellow-taxi", mapping);

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
