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

  query_options options = {};
  options.scroll = "1m";
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

  SearchResult next_page = response.next();

  Console.WriteLine("Successfully retrieved " + next_page.fetched + " documents");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}