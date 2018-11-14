String doc1 = "{\"capacity\": 4}";
String doc2 = "{\"capacity\": 7}";

try {
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-id", doc1);
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-other-id", doc2);

    String newDocuments = "["
        + "{"
        + "     \"_id\": \"some-id\","
        + "     \"body\": { \"category\": \"suv\" }"
        + "},"
        + "{"
        + "     \"_id\": \"some-other-id\","
        + "     \"body\": { \"category\": \"limousine\" }"
        + "}"
        + "]";

    String response = kuzzle.getDocument().mReplace(
      "nyc-open-data",
      "yellow-taxi",
      newDocuments
    );

    System.out.println(response);
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}