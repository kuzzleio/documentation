try {
  kuzzle->auth->login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  std::vector<kuzzleio::user_right*> rights = kuzzle->auth->getMyRights();

  for (int i = 0; rights[i]; i++) {
    std::cout << rights[i]->controller << " " << rights[i]->action << std::endl;
    std::cout << rights[i]->index << " " << rights[i]->collection << std::endl;
    std::cout << rights[i]->value << std::endl;
  }
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.getMessage() << std::endl;
}
