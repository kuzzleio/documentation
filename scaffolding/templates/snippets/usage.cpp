try {
  kuzzle->{{controller}}->{{action}}();
  std::cout << "{{success_message}}" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
