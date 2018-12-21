try {
  kuzzle->auth->login("local", R"({"username":"foo","password":"bar"})");
  kuzzle->auth->updateMyCredentials(
    "local",
    R"({"username":"foo","password":"bar","other":"value"})"
  );

  std::cout << "Credentials successfully updated" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
