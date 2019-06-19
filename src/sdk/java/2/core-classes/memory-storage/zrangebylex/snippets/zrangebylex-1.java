
kuzzle.memoryStorage.zrangebylex("key", "-", "(g", new ResponseListener<String[]>() {
  @Override
  public void onSuccess(String[] members) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
