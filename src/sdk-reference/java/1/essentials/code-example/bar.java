try {
  kuzzle.getIndex().create("my_index");
  System.out.println("bar");
} catch (KuzzleException e) {
  System.out.println (e.getMessage());
}