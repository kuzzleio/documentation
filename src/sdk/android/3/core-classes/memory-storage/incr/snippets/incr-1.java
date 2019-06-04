
kuzzle.memoryStorage.incr("key", new ResponseListener<Long>() {
  @Override
  public void onSuccess(int value) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
