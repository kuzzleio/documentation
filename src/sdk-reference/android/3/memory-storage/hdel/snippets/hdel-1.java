
String[] fields = {"field1", "field2", "..."};

kuzzle.memoryStorage.hdel("key", fields, new ResponseListener<Long>() {
  @Override
  public void onSuccess(int count) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
