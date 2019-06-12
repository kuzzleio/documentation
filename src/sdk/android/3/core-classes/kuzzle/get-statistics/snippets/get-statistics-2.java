
// Date can be either in ISO format or a timestamp (utc, in milliseconds)
kuzzle.getStatistics("2015-11-15T13:36:45.558Z", new ResponseListener<JSONObject[]>() {
  @Override
  public void onSuccess(JSONObject[] statistics) {
    // ...
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
};
