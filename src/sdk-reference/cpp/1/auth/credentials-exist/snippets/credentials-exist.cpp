try {
  kuzzle->auth->login("local", R"({"username":"foo","password":"bar"})");
  bool exists = kuzzle->auth->credentialsExist("local");

  if (exists) {
    std::cout << "Credentials exists for local strategy" << std::endl;
  }
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
