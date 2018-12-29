try {
  kuzzle->auth->login("local", R"({"username":"foo","password":"bar"})");
  kuzzleio::User user = kuzzle->auth->getCurrentUser();

  std::cout << "Successfully got current user" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
