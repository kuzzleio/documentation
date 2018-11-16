
kuzzle.memoryStorage.ping(new ResponseListener<String>() {
  @Override
  public void onSuccess(String response) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
