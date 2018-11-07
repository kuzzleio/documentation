try {
  bool exists = kuzzle->server->adminExists();

  std::cout << "Admin exists? " << exists << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
