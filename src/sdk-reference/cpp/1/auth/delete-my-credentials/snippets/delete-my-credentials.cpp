try {
  kuzzle->auth->login("local", R"({"username":"foo","password":"bar"})");
  kuzzle->auth->deleteMyCredentials("local");

  std::cout << "Credentials Successfully deleted" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
