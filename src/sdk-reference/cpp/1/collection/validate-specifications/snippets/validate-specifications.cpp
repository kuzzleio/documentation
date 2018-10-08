try {
  std::string specifications = "{ \"nyc-open-data\": { \"yellow-taxi\": { \"strict\": false, \"fields\": { \"license\": { \"mandatory\": true, \"type\": \"string\" } } } } }";
  kuzzleio::validation_response *validation_response = kuzzle->collection->validateSpecifications(specifications);

  if (validation_response.valid) {
    std::cout << "Success" << std::endl;
  }
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
