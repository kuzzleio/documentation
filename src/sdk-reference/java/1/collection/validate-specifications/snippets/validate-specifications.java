try {
    String specifications = "{ \"nyc-open-data\": { \"yellow-taxi\": { \"strict\": false, \"fields\": { \"license\": { \"mandatory\": true, \"type\": \"string\" } } } } }";
    if (kuzzle.getCollection().validateSpecifications(specifications)) {      
      System.out.println("Success");
    }
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
