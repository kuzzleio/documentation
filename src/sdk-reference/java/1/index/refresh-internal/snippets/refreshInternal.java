try{
  kuzzle.getIndex().refreshInternal();

  System.out.println("Internal index successfully refreshed");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
