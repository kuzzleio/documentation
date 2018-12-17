try {
  kuzzle_request request = {0};
  request.controller = "document";
  request.action = "create";
  request.index = "nyc-open-data";
  request.collection = "yellow-taxi";
  request.id = "my-custom-document-id";
  request.body = "{\"trip_distance\": 4.23, \"passenger_count\": 2}";

  QueryOptions options = new QueryOptions();
  options.refresh = "wait_for";

  kuzzle_response* response = kuzzle.query(request, options);

  if (response.status == 200) {
    Console.WriteLine("Document created");
  }
}
catch (UnauthorizedException e) {
  Console.Error.WriteLine("You are not connected");
}
catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
