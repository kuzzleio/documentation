
kuzzle.memoryStorage.hget("key", "field1", new ResponseListener<String>() {
  @Override
  public void onSuccess(String value) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
