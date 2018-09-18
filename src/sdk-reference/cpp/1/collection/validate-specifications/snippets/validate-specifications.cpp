try {
  std::string specifications = "{ \"nyc-open-data\": { \"yellow-taxi\": { \"strict\": true, \"fields\": { \"license\": { \"mandatory\": true, \"type\": \"string\" } } } } }";

  if (kuzzle->collection->validateSpecifications(specifications)) {
    std::cout << "Success" << std::endl;
  }
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
