
kuzzle.memoryStorage.spop("key", new ResponseListener<String[]>() {
  @Override
  public void onSuccess(String[] elements) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
