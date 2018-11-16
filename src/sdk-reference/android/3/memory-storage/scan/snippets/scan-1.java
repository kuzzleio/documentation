
kuzzle.memoryStorage.scan(0, new ResponseListener<JSONObject>() {
  @Override
  public void onSuccess(JSONObject page) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
