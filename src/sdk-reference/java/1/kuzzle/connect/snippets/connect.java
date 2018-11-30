try {
  kuzzle.connect();
  System.out.println("Successfully connected");
} catch(KuzzleException e) {
  System.err.println(e.getMessage());
}
