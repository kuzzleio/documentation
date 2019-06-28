
kuzzle.memoryStorage.hexists("key", "field1", new ResponseListener<Boolean>() {
  @Override
  public void onSuccess(Boolean exists) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
