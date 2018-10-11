try {
    String specifications = "{ \"strict\": false, \"fields\": { \"license\": { \"mandatory\": true, \"type\": \"string\" } } }";
    ValidationResponse validationResponse = kuzzle.getCollection().validateSpecifications("nyc-open-data", "yellow-taxi", specifications);

    if (validationResponse.getValid()) {
      System.out.println("Success");
    }
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
