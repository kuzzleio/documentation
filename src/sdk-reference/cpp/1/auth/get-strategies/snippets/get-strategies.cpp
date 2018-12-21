try {
  kuzzle->auth->login("local", R"({"username":"foo","password":"bar"})");
  std::vector<std::string> strategies = kuzzle->auth->getStrategies();

  for (auto strategy: strategies) {
    std::cout << strategy << std::endl;
  }
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
