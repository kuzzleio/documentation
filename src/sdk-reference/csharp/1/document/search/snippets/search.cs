try {
  for (size_t i = 0; i < 5; i++) {
    kuzzle.document.create("nyc-open-data", "yellow-taxi", "", @"{
      ""category"": ""suv""
    }");
  }
  for (size_t i = 5; i < 15; i++) {
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
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
