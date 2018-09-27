try {
    String specifications = "{ \"nyc-open-data\": { \"yellow-taxi\": { \"strict\": false, \"fields\": { \"license\": { \"mandatory\": true, \"type\": \"string\" } } } } }";
    ValidationResponse validationResponse = kuzzle.getCollection().validateSpecifications(specifications);

    if (validationResponse.getValid()) {
      System.out.println("Success");
    }
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
