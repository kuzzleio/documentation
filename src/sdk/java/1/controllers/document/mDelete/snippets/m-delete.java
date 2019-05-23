StringVector ids = new StringVector();

ids.add("some-id");
ids.add("some-other-id");

try {
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-id", "{}");
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-other-id", "{}");

    StringVector deleted = kuzzle.getDocument().mDelete("nyc-open-data", "yellow-taxi", ids);

    System.out.println(String.format("Successfully deleted %d documents", deleted.size()));
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
