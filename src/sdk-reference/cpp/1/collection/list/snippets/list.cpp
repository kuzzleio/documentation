try {
  kuzzleio::query_options options = {0};
  options.from = 1;
  options.size = 2;

  std::string collection_list = kuzzle->collection->list("mtp-open-data", &options);
  // {"type":"all","collections":[{"name":"pink-taxi","type":"stored"}],"from":1,"size":2}
  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
