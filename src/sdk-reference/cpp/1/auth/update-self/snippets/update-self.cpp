try {
  kuzzle->auth->login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  User updatedUser = kuzzle->auth->updateSelf("{\"foo\":\"bar\"}");

  // Prints: {"foo":"bar","profileIds":["default"]}
  std::cout << updatedUser.content() << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
