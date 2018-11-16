try {
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-id", "{}");
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-other-id", "{}");

    String newDocuments = "["
        + "{"
        + "     \"_id\": \"some-id\","
        + "     \"body\": { \"capacity\": 4 }"
        + "},"
        + "{"
        + "     \"_id\": \"some-other-id\","
        + "     \"body\": { \"capacity\": 4 }"
        + "}"
        + "]";

    String response = kuzzle.getDocument().mReplace(
      "nyc-open-data",
      "yellow-taxi",
      newDocuments
    );

    System.out.println(response);
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
