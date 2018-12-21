try {
  kuzzle->auth->login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  kuzzle->auth->updateMyCredentials("local", "{\"username\":\"foo\",\"password\":\"bar\",\"other\":\"value\"}");

  std::cout << "Credentials successfully updated" << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
