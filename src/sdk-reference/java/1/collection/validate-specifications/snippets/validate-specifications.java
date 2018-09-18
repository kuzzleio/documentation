try {
    String specifications = "{ \"nyc-open-data\": { \"yellow-taxi\": { \"strict\": true, \"fields\": { \"license\": { \"mandatory\": true, \"type\": \"string\" } } } } }";
    if (kuzzle.getCollection().validateSpecifications(specifications)) {      
      System.out.println("Success");
    }
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
