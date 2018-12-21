try {
  kuzzle->auth->login("local", R"({"username":"foo","password":"bar"})");
  kuzzleio::User user = kuzzle->auth->getCurrentUser();

  std::cout << "Successfully get current user" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
