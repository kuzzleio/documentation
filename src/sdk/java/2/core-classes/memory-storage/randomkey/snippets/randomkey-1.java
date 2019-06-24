
kuzzle.memoryStorage.randomkey(new ResponseListener<String>() {
  @Override
  public void onSuccess(String key) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
