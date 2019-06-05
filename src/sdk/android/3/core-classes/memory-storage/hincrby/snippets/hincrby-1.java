
kuzzle.memoryStorage.hincrby("key", "field", 42, new ResponseListener<Long>() {
  @Override
  public void onSuccess(int value) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
