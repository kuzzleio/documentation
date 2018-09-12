try {
    String mapping = "{\"properties\": {\"license\": {\"type\": \"keyword\"}}}";

    kuzzle.getCollection().create("nyc-open-data", "yellow-taxi", mapping);

    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
