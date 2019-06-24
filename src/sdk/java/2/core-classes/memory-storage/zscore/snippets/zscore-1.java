
kuzzle.memoryStorage.zscore("key", "bar", new ResponseListener<Double>() {
  @Override
  public void onSuccess(double score) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
