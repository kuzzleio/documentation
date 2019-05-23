try {
  std::string specifications = R"({
    "strict": false,
    "fields": {
      "license": {
        "mandatory": true,
        "type": "string"
      }
    }
  })";

  kuzzleio::validation_response *validation_response =
    kuzzle->collection->validateSpecifications(
      "nyc-open-data",
      "yellow-taxi",
      specifications);

  if (validation_response->valid) {
    std::cout << "Specifications are valid" << std::endl;
  }
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
