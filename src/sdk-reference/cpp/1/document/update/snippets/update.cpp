try {
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-id", R"({"capacity": 4})");
  std::string response = kuzzle->document->update("nyc-open-data", "yellow-taxi", "some-id", R"({"category": "sedan"})");

  std::cout << response << std::endl;
  // std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
