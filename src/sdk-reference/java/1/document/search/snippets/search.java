try {
  for (int i = 0; i < 5; i++) {
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "", "{" +
      "\"category\": \"suv\"" +
    "}");
  }
  for (int i = 5; i < 15; i++) {
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "", "{" +
      "\"category\": \"limousine\"" +
    "}");
  }
  kuzzle.getIndex().refresh("nyc-open-data");

  QueryOptions options = new QueryOptions();
  options.setFrom(0);
  options.setSize(2);

  SearchResult results = kuzzle.getDocument().search(
    "nyc-open-data",
    "yellow-taxi",
    "{" +
      "\"query\": {" +
        "\"match\": {" +
          "\"category\": \"suv\"" +
        "}" +
      "}" +
    "}",
    options);

  System.out.println("Successfully retrieved " + results.getTotal() + " documents");
} catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
