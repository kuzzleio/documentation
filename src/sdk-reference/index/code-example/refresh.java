try{
  kuzzle.getIndex().mDelete(indexes);

  System.out.println("O shards fail to refresh");
} catch (KuzzleException e) {
    System.out.println(e.getMessage());
}
