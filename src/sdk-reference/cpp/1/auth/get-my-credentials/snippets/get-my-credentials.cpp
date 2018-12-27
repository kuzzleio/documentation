try {
  kuzzle->auth->login("local", R"({"username":"foo","password":"bar"})");
  std::string local_credentials = kuzzle->auth->getMyCredentials("local");

  std::cout << local_credentials << std::endl;
  std::cout << "Successfully got local credentials" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
