try {
    if (kuzzle.getCollection().exists("nyc-open-data", "green-taxi")) {
      System.out.println("Success");
    }
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
