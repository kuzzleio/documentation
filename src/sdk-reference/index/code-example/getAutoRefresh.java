if (kuzzle.getIndex().getAutoRefresh("nyc-open-data")) {
  System.out.println("autorefresh is true");
} else {
  System.out.println("autorefresh is false");
}
