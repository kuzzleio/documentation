
String[] keys = new String[]{"key1", "key2", "..."};

kuzzle.memoryStorage.sunion(keys, new ResponseListener<String[]>() {
  @Override
  public void onSuccess(String[] values) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
