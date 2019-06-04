
kuzzle.memoryStorage.hvals("key", new ResponseListener<String[]>() {
  @Override
  public void onSuccess(String[] values) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
