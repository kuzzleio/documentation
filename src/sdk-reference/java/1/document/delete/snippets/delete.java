try {
    String id = kuzzle.getDocument().delete("nyc-open-data", "yellow-taxi", "some-id");

    if (id.equals("some-id")) {
        System.out.println("Success");
    }
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
