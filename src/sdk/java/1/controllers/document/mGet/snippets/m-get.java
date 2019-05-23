StringVector ids = new StringVector();

ids.add("some-id");
ids.add("some-other-id");


try {
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-id", "{}");
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-other-id", "{}");

    String response = kuzzle.getDocument().mGet("nyc-open-data", "yellow-taxi", ids);

    System.out.println(response);
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
