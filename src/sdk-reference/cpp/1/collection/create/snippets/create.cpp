try {
  std::string mapping = "{\"properties\": {\"license\": {\"type\": \"keyword\"}}}";

  kuzzle->collection->create("nyc-open-data", "yellow-taxi", &mapping);

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
