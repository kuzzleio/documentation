try {
    int count = kuzzle.getDocument().count("nyc-open-data", "yellow-taxi", "{\"query\": {\"match\": {\"licence\": \"valid\"}}}");

    System.out.println("Found " + count + " documents matching licence:valid");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
