try {
  std::string jwt = kuzzle->auth->login(
    "local",
    R"({"username":"foo","password":"bar"})");

  kuzzleio::token_validity* res = kuzzle->auth->checkToken(jwt);

  if (res->valid)
    std::cout << "Success" << std::endl;
  else
    std::cerr << res->state << std::endl;
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
