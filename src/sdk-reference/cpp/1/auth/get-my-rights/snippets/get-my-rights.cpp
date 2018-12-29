try {
  kuzzle->auth->login("local", R"({"username":"foo","password":"bar"})");

  std::vector<std::shared_ptr<kuzzleio::UserRight>> rights =
    kuzzle->auth->getMyRights();

  for (auto right : rights) {
    std::cout << right->controller() << " " << right->action() << std::endl;
    std::cout << right->index() << " " << right->collection() << std::endl;
    std::cout << right->value() << std::endl;
  }
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
