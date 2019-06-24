
kuzzle.memoryStorage.incrbyfloat("key", -3.14159, new ResponseListener<Double>() {
  @Override
  public void onSuccess(double value) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
