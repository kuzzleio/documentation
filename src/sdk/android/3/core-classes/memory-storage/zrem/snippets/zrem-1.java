
String[] members = new String[]{"key1", "key2", "..."};

kuzzle.memoryStorage.zrem("key", members, new ResponseListener<Long>() {
  @Override
  public void onSuccess(int count) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
