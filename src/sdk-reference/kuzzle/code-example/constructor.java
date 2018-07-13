try {
  Options options = new Options();
  options.autoResubscribe = false;

  Kuzzle kuzzle = new Kuzzle("kuzzle", options);

  System.out.println("Everything is ok");
} catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
