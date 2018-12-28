try {
  bool valid = kuzzle->document->validate("nyc-open-data", "yellow-taxi", R"({
    "capacity": 4
  })");

  if (valid) {
    std::cout << "The document is valid" << std::endl;
  }
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
