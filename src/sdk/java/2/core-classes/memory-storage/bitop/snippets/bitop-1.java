
String[] sourceKeys = {"srckey1", "srckey2", "..."};

kuzzle.memoryStorage.bitop("key", "AND", sourceKeys, new ResponseListener<Long>() {
  @Override
  public void onSuccess(int length) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
