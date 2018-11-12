try {
  kuzzle->auth->login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  kuzzle->auth->updateMyCredentials("local", "{\"username\":\"foo\",\"password\":\"bar\",\"other\":\"value\"}");

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
