try {
    int i;

    for (i = 0; i < 5; i++) {
        kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "", "{\"category\": \"suv\"}");
    }
    for (i = 5; i < 15; i++) {
        kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "", "{\"category\": \"limousine\"}");
    }
    kuzzle.getIndex().refresh("nyc-open-data");

    SearchResult response = kuzzle.getDocument().search("nyc-open-data", "yellow-taxi", "{\"query\":{\"match\": {\"category\": \"suv\"}}}");

    System.out.println(String.format("Successfully retrieved %d documents", response.getTotal()));
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
