try {
  std::string response;
  response = kuzzle->document->delete_("nyc-open-data", "yellow-taxi", "some-id");

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.what() << std::endl;
}
