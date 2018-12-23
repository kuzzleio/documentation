try {
  bool exists = kuzzle->server->adminExists();

  if (exists) {
    std::cout << "Admin user exists" << std::endl;
  }
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
