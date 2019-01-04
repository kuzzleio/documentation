try {
  QueryOptions options = new QueryOptions();
  options.from = 1;
  options.size = 2;

  string collection_list = kuzzle.collection.list("mtp-open-data", options);

  Console.WriteLine(collection_list);
  // {"type":"all","collections":[{"name":"pink-taxi","type":"stored"}],"from":1,"size":2}
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
