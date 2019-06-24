
kuzzle.memoryStorage.zincrby("key", "foo", 3.14159, new ResponseListener<double>() {
  @Override
  public void onSuccess(double value) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
