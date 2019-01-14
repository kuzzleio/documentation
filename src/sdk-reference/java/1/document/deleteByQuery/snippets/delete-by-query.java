try {
  StringVector response =
    kuzzle.getDocument().deleteByQuery("nyc-open-data", "yellow-taxi", "{" +
      "\"query\": {" +
        "\"term\": {" +
          "\"capacity\": 7" +
        "}" +
      "}" +
    "}");

  System.out.println("Successfully deleted " + response.size() + " documents");
} catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
