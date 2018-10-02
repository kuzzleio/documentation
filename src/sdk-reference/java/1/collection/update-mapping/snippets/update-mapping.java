try {
    String mapping = "{\"properties\": {\"plate\": {\"type\": \"keyword\"}}}";

    kuzzle.getCollection().updateMapping("nyc-open-data", "yellow-taxi", mapping);

    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
