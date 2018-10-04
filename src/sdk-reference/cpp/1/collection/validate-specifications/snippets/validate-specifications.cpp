try {
  std::string specifications = "{ \"strict\": false, \"fields\": { \"license\": { \"mandatory\": true, \"type\": \"string\" } } }";

  if (kuzzle->collection->validateSpecifications("nyc-open-data", "yellow-taxi", specifications)) {
    std::cout << "Success" << std::endl;
  }
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
