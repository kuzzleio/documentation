
String[] fields = {"field1", "field2"};

kuzzle.memoryStorage.hmget("key", fields, new ResponseListener<String[]>() {
  @Override
  public void onSuccess(String[] values) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
