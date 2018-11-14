std::vector<std::string> ids;

ids.push_back("some-id");
ids.push_back("some-other-id");

try {
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-id", "{}");
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-other-id", "{}");

  std::vector<std::string> deleted = kuzzle->document->mDelete("nyc-open-data", "yellow-taxi", ids);

  std::cout << "Successfully deleted " << deleted.size() << " documents" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}