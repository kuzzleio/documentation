
kuzzle.memoryStorage.hgetall("key", new ResponseListener<JSONObject>() {
  @Override
  public void onSuccess(JSONObject hash) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
