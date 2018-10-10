try {
  kuzzle->index->create("nyc-open-data");
} catch (kuzzleio::GatewayTimeoutException e) {
  /* Won't be called */
} catch (kuzzleio::BadRequestException e) {
  std::cout << "Status: " << e.status << " Message: " << e.getMessage() << std::endl;
  std::cout << "Try with an other name!" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  /* Won't be called */
}
