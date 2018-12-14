try {
  QueryOptions options = new QueryOptions();
  options.from = 1;
  options.size = 2;

  string collection_list = kuzzle.collection.list("mtp-open-data", options);
  // {"type":"all","collections":[{"name":"pink-taxi","type":"stored"}],"from":1,"size":2}
  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}
