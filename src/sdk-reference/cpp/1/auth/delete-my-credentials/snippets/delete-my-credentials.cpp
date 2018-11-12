try {
  kuzzle->auth->login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  kuzzle->auth->deleteMyCredentials("local");

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
