try {
  std::string jwt = kuzzle->auth->login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}