
kuzzle.memoryStorage.zrevrank("key", "member", new ResponseListener<Long>() {
  @Override
  public void onSuccess(int position) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
