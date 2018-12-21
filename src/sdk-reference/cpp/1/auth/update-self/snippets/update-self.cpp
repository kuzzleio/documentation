try {
  kuzzle->auth->login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  kuzzleio::User updatedUser = kuzzle->auth->updateSelf("{\"age\": 42}");

  std::cout << updatedUser.content() << std::endl;
  // {"age": 42,"profileIds": ["default"]}
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
