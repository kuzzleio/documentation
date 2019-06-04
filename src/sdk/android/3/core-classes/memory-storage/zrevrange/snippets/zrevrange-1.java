
kuzzle.memoryStorage.zrevrange("key", 0, -1, new ResponseListener<JSONObject[]>() {
  @Override
  public void onSuccess(JSONObject[] members) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
