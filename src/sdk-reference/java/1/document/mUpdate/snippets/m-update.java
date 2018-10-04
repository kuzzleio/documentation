try {
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-id", "{\"capacity\": 4}");
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-other-id", "{\"\capacity\": 7}");

    String body = "["
        + "{"
        + "     \"id\": \"some-id\","
        + "     \"body\": { \"category\": \"sedan\" }"
        + "},"
        + "{"
        + "     \"id\": \"some-other-id\","
        + "     \"body\": { \"category\": \"limousine\" }"
        + "}"
        + "]";

    String response = kuzzle.getDocument().mReplace("nyc-open-data", "yellow-taxi", body);
    
    System.out.println(response);
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}