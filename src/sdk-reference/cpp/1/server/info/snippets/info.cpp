try {
  std::string kuzzle_info = kuzzle->server->info();

  std::cout << kuzzle_info << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
