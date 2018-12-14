try {
  QueryOptions options;
  options.from = 0;
  options.size = 2;

  SearchResult* response = kuzzle.collection.searchSpecifications(R"({
    "query": {
      "match_all": {}
    }
  })", options);

  Console.WriteLine("Successfully retrieved " + response.fetched + " specifications");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}