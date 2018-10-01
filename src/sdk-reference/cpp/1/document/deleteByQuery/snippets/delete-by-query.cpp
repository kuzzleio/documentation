try {
  std::vector<std::string> response;
  response = kuzzle->document->deleteByQuery("nyc-open-data", "yellow-taxi", "{\"query\": {\"match\": {\"capacity\": 7}}}");

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}