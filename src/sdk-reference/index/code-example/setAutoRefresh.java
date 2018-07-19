try {
  kuzzle.getIndex().setAutoRefresh("nyc-open-data", true);

  System.out.println("autorefresh flag is set to true");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
