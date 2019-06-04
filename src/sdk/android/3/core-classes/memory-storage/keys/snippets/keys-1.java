
kuzzle.memoryStorage.keys("foo*", new ResponseListener<String[]>() {
  @Override
  public void onSuccess(String[] keys) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
