try {
  for (int i = 0; i < 5; i++) {
    kuzzle.document.create("nyc-open-data", "yellow-taxi", "", @"{
      ""category"": ""suv""
    }");
  }
  for (int i = 5; i < 15; i++) {
    kuzzle.document.create("nyc-open-data", "yellow-taxi", "", @"{
      ""category"": ""limousine""
    }");
  }
  kuzzle.index.refresh("nyc-open-data");

  QueryOptions options = new QueryOptions();
  options.from = 0;
  options.size = 2;

  SearchResult response = kuzzle.document.search(
    "nyc-open-data",
    "yellow-taxi",
    @"{
      ""query"": {
        ""match"": {
          ""category"": ""suv""
        }
      }
    }",
    options);

  Console.WriteLine("Successfully retrieved " + response.total + " documents");
} catch  {
  Console.Error.WriteLine("");
}
