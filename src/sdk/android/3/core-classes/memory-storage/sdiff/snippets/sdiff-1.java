
String[] keys = new String[]{"key1", "key2", "..."};

kuzzle.memoryStorage.sdiff("key", keys, new ResponseListener<String[]>() {
  @Override
  public void onSuccess(String[] diffs) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
