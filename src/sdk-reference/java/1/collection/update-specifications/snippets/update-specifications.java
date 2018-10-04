try {
    String specifications = "{ \"nyc-open-data\": { \"yellow-taxi\": { \"strict\": false, \"fields\": { \"license\": { \"mandatory\": true, \"type\": \"string\" } } } } }";

    String response = kuzzle.getCollection().updateSpecifications("nyc-open-data", "yellow-taxi", specifications);

    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
