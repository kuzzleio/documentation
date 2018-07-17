try {
  kuzzle.getIndex().setAutoRefresh("nyc-open-data", true);

  System.out.println("autorefresh flag is set to true");
} catch (kuzzleio::KuzzleException e) {
    System.out.println(e.getMessage());
}
