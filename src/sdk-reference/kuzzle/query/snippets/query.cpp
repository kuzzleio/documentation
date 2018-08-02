try {
  kuzzle_request request = {0};
  request.controller = "document";
  request.action = "create";
  request.index = "nyc-open-data";
  request.collection = "yellow-taxi";
  request.id = "my-custom-document-id";
  request.body = "{\"trip_distance\": 4.23, \"passenger_count\": 2}";

  query_options options = {0};
  options.refresh = "wait_for";

  kuzzle_response* response = kuzzle->query(&request, &options);

  if (response->status == 200) {
    std::cout << "Document created" << std::endl;
  }
}
catch (kuzzleio::UnauthorizedException& e) {
  std::cerr << "You are not connected" << std::endl;
}
catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.getMessage() << std::endl;
}
