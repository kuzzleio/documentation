String doc = "{\"capacity\": 4}";

try {
   boolean valid = kuzzle.getDocument().validate("nyc-open-data", "yellow-taxi", doc);

    if (valid) {
      System.out.println("Success");
    }
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
