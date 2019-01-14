try {
  String id = kuzzle.getDocument().delete(
    "nyc-open-data",
    "yellow-taxi",
    "some-id");

  System.out.println("Document " + id + " successfully deleted");
} catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
