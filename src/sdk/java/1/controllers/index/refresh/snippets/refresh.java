try{
  kuzzle.getIndex().refresh("nyc-open-data");

  System.out.println("0 shards fail to refresh");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
