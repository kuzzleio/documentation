
kuzzle.memoryStorage.time(new ResponseListener<Long[]>() {
  @Override
  public void onSuccess(Long[] result) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
