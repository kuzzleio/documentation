
kuzzle.memoryStorage.sismember("key", "member", new ResponseListener<Boolean>() {
  @Override
  public void onSuccess(Boolean isMember) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
