try {
  std::string jwt = kuzzle->auth->login("local", R"({"username":"foo","password":"bar"})");

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
