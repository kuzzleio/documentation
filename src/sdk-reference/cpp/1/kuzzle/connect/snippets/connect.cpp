try {
  kuzzle->connect();
  std::cout << "Successfully connected" << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
