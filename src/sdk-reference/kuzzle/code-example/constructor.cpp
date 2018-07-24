options options = {0};
options.auto_resubscribe = false;

try {
  kuzzleio::Kuzzle kuzzle = new Kuzzle("kuzzle", &options);

  std::cout << "Everything is ok" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std:cerr << "Error: " << e.getMessage() << std::endl;
}
