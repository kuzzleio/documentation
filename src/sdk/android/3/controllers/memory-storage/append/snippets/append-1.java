
kuzzle.memoryStorage.append("key", "value", new ResponseListener<Long>() {
  @Override
  public void onSuccess(int newLength) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
