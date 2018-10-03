try {
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-id", "{}");
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-other-id", "{}");

    String body = "["
        + "{"
        + "     \"id\": \"some-id\","
        + "     \"body\": { \"capacity\": 4 }"
        + "},"
        + "{"
        + "     \"id\": \"some-other-id\","
        + "     \"body\": { \"capacity\": 4 }"
        + "}"
        + "]";

    String response = kuzzle.getDocument().mReplace("nyc-open-data", "yellow-taxi", body);
    
    System.out.println(response);
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
