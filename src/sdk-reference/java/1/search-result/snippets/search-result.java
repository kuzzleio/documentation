try {
    int i;

    for (i = 0; i < 5; i++) {
        kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "", "{\"category\": \"suv\"}");
    }
    for (i = 5; i < 15; i++) {
        kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "", "{\"category\": \"limousine\"}");
    }
    kuzzle.getIndex().refresh("nyc-open-data");

    QueryOptions options = new QueryOptions();
    options.setScroll("1m");
    options.setSize(2);

    SearchResult response = kuzzle.getDocument().search("nyc-open-data", "yellow-taxi", "{\"query\":{\"match\": {\"category\": \"suv\"}}}", options);
    SearchResult nextPage = response.next();

    System.out.println(String.format("Successfully retrieved %d documents", nextPage.getFetched()));
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}