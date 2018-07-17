try{
  kuzzle.getIndex().refreshInternal();

  System.out.println("Internal index successfully refreshed");
} catch (kuzzleio::KuzzleException e) {
    System.out.println(e.getMessage());
}
