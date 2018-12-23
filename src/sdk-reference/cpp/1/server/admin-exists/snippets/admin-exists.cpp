try {
  bool exists = kuzzle->server->adminExists();

  if (exists) {
    std::cout << "Admin user exists" << std::endl;
  } else {
    std::cout << "Admin user does not exists" << std::endl;
  }
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
