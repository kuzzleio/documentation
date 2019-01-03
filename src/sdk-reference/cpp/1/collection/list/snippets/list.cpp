try {
  kuzzleio::query_options options;
  options.from = 1;
  options.size = 2;

  std::string collection_list = kuzzle->collection->list("mtp-open-data", &options);

  std::cout << collection_list << std::endl;
  // {"type":"all","collections":[{"name":"pink-taxi","type":"stored"}],"from":1,"size":2}
} catch (kuzzleio::KuzzleException &e) {
  std::cerr << e.what() << std::endl;
}
