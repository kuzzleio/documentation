try {
    QueryOptions options = new QueryOptions();
    options.setScroll("1m");
    options.setSize(2);

    SearchResult response = kuzzle.getCollection().searchSpecifications(
      "{\"query\":{\"match_all\": {}}}",
      options
    );

    System.out.println(String.format("Successfully retrieved %d specifications", response.getTotal()));
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
