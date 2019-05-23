try {
  kuzzle->auth->login("local", R"({"username":"foo","password":"bar"})");
  kuzzleio::User updatedUser = kuzzle->auth->updateSelf(R"({"age": 42})");

  std::cout << updatedUser.content() << std::endl;
  // {"age": 42}
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
