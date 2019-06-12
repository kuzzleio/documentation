
kuzzle.memoryStorage.zrangebyscore("key", 2, 3, new ResponseListener<JSONObject[]>() {
  @Override
  public void onSuccess(JSONObject[] members) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
