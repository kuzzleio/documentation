std::vector<std::string> ids;

ids.push_back("some-id");
ids.push_back("some-other-id");

try {
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-id", "{\"capacity\": 4}");
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-other-id", "{\"capacity\": 4}");

  std::string response = kuzzle->document->mGet("nyc-open-data", "yellow-taxi", ids);

  std::cout << response << std::endl;
  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}