
kuzzle.memoryStorage.hscan("key", 0, new ResponseListener<JSONObject>() {
  @Override
  public void onSuccess(JSONObject page) {
  }

  @Override
  public void onError(JSONObject error) {
  }
});
