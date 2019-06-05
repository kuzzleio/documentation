
String[] keys = new String[]{"key1", "key2", "..."};

kuzzle.memoryStorage.pfmerge('key', keys, new ResponseListener<Void>() {
  @Override
  public void onSuccess(Void v) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
