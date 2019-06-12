try {
    String mapping = kuzzle.getCollection().getMapping("nyc-open-data", "yellow-taxi");
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
