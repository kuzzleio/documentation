try {
  KuzzleRequest request = new KuzzleRequest();
  request.setController("document");
  request.setAction("create");
  request.setIndex("nyc-open-data");
  request.setCollection("yellow-taxi");
  request.setId("my-custom-document-id");
  request.setBody("{\"trip_distance\": 4.23, \"passenger_count\": 2}");

  QueryOptions options = new QueryOptions();
  options.setRefresh("wait_for");

  KuzzleResponse response = kuzzle.query(request, options);

  if (response != null) {
    System.out.println("Document created");
  }
}
catch (UnauthorizedException e) {
  System.err.println("You are not connected");
}
catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
