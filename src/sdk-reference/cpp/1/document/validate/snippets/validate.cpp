try {
  if (kuzzle->document->validate("nyc-open-data", "yellow-taxi", R"({
    "capacity": 4
  })")) {
    std::cout << "Success" << std::endl;
  }
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
