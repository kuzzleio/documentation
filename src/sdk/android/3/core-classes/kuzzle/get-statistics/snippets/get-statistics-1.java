
kuzzle.getStatistics(new ResponseListener<JSONObject>() {
  @Override
  public void onSuccess(JSONObject[] statistics) {
    // ...
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
});
