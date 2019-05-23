
kuzzle.memoryStorage.hincrbyfloat("key", "field", 3.14159, new ResponseListener<Double>() {
  @Override
  public void onSuccess(int value) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
