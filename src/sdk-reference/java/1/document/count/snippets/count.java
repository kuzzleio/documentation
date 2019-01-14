try {
  int count = kuzzle.getDocument().count("nyc-open-data", "yellow-taxi", "{" +
    "\"query\": {" +
      "\"match\": {" +
        "\"license\": \"valid\"" +
      "}" +
    "}" +
  "}");

  System.out.println("Found " + count + " documents matching license:valid");
} catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
