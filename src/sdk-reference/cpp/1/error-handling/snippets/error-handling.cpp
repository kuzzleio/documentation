try {
  kuzzle->index->create("nyc-open-data");
} catch (kuzzleio::BadRequestException& e) {
  std::cout << "Status: " << e.status << " Message: " << e.what() << std::endl;
  std::cout << "Try with another name!" << std::endl;
}
