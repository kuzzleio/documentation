try {
  kuzzleio::kuzzle_request request;
  request.controller = "document";
  request.action = "create";
  request.index = "nyc-open-data";
  request.collection = "yellow-taxi";
  request.id = "my-custom-document-id";
  request.body = "{\"trip_distance\": 4.23, \"passenger_count\": 2}";

  kuzzleio::query_options options;
  options.refresh = "wait_for";

  kuzzleio::kuzzle_response* response = kuzzle->query(request, options);

  std::cout << "Document created" << std::endl;
}
catch (kuzzleio::UnauthorizedException& e) {
  std::cerr << "You are not connected" << std::endl;
}
catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
