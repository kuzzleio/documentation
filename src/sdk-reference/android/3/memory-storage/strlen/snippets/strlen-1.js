
kuzzle.memoryStorage.strlen("key", new ResponseListener<Long>() {
  @Override
  public void onSuccess(int length) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
