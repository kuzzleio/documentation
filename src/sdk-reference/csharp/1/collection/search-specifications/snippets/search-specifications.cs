try {
  QueryOptions options = new QueryOptions();
  options.from = 0;
  options.size = 2;

  SearchResult response = kuzzle.collection.searchSpecifications(@"{
    ""query"": {
      ""match_all"": {}
    }
  }", options);

  Console.WriteLine("Successfully retrieved " + response.fetched + " specifications");
} catch  {
  Console.Error.WriteLine("");
}