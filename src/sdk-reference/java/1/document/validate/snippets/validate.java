try {
  boolean valid = kuzzle.getDocument().validate("nyc-open-data", "yellow-taxi", "{" +
    "\"capacity\": 4" +
  "}");

  if (valid) {
    System.out.println("The document is valid");
  }
} catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
