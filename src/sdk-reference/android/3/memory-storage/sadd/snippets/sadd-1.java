
String[] items = new String[]{"foo", "bar", "baz"};

kuzzle.memoryStorage.sadd("key", items, new ResponseListener<Long>() {
  @Override
  public void onSuccess(int count) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
