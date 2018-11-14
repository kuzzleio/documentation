try {
    StringVector deleted = kuzzle.getDocument().deleteByQuery(
      "nyc-open-data",
      "yellow-taxi",
      "{\"query\": {\"term\": {\"capacity\": 7}}}"
    );

    System.out.println(String.format("Successfully deleted %d documents", deleted.size()));
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
