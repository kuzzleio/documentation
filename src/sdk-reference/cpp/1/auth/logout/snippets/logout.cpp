try {
  kuzzle->auth->login("local", R"({"username":"foo","password":"bar"})");
  kuzzle->auth->logout();

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
