try {
  for (uint i = 0; i < 5; i++) {
    kuzzle.document.create("nyc-open-data", "yellow-taxi", "", @"{
      ""category"": ""suv""
    }");
  }
  for (uint i = 5; i < 15; i++) {
    kuzzle.document.create("nyc-open-data", "yellow-taxi", "", @"{
      ""category"": ""limousine""
    }");
  }
  kuzzle.index.refresh("nyc-open-data");

  query_options options = {};
  options.scroll = "1m";
  options.size = 2;

  SearchResult first_page = kuzzle.document.search(
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

  SearchResult second_page = first_page.next();

  Console.WriteLine("Successfully retrieved " + second_page.fetched + " documents");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
