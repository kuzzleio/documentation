try {
  std::string specifications = kuzzle->collection->getSpecifications("nyc-open-data", "yellow-taxi");

  std::cout << specifications << std::endl;
  // {"nyc-open-data": {"yellow-taxi": {"strict": false, "fields": {"license": {"type": "string"}}}}}
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
