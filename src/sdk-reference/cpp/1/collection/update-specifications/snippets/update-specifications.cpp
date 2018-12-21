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

  std::string updated_specifications =
    kuzzle->collection->updateSpecifications("nyc-open-data", "yellow-taxi", specifications);

  std::cout << updated_specifications << std::endl;
  // {"strict":false,"fields":{"license":{"mandatory":true,"type":"string"}}}
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
