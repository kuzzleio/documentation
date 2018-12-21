try {
  kuzzle->auth->login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  std::string local_credentials = kuzzle->auth->getMyCredentials("local");

  std::cout << local_credentials << std::endl;
  std::cout << "Successfully get local credentials" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
