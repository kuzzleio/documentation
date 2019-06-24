
kuzzle.memoryStorage.getbit("key", 10, new ResponseListener<Integer>() {
  @Override
  public void onSuccess(int bit) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
