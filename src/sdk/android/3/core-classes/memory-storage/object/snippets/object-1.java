
kuzzle.memoryStorage.object("key", "encoding", new ResponseListener<String>() {
  @Override
  public void onSuccess(String property) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
