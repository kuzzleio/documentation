try {
    String specifications = "{ \"strict\": false, \"fields\": { \"license\": { \"mandatory\": true, \"type\": \"string\" } } }";
    if (kuzzle.getCollection().validateSpecifications("nyc-open-data", "yellow-taxi", specifications)) {
      System.out.println("Success");
    }
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
