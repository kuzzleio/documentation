
kuzzle.memoryStorage.pttl("key", new ResponseListener<Long>() {
  @Override
  public void onSuccess(int ttl) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
