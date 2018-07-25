try {
  user* currentUser = kuzzle->auth->getCurrentUser();

  std::cout << currentUser->id << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
