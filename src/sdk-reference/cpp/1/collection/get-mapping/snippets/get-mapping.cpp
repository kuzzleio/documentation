try {
  std::string mapping = kuzzle->collection->getMapping("nyc-open-data", "yellow-taxi");

  std::cout << mapping << std::endl;
  // {"dynamic": "false","_meta": { "area": "Panipokhari" }, "properties":{"license":{"type":"keyword"},"driver":{"properties":{"name":{"type":"keyword"},"curriculum":{"type":"text"}}}}}
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
