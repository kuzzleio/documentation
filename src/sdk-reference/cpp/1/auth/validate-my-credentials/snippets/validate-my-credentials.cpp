try {
  kuzzle->auth->login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  bool valid = kuzzle->auth->validateMyCredentials("local", "{\"username\":\"foo\",\"password\":\"bar\"}");

  if (valid) {
    std::cout << "Credentials are valid" << std::endl;
  }
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
