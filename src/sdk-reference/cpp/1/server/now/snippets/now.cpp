try {
  long long timestamp = kuzzle->server->now();

  std::cout << "Epoch-millis timestamp: " << timestamp << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
