
kuzzle.memoryStorage.persist("key", new ResponseListener<Boolean>() {
  @Override
  public void onSuccess(Boolean status) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
