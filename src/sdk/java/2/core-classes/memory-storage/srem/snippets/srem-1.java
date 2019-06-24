
String[] members = new String[]{"member1", "member2", "..."};

kuzzle.memoryStorage.srem("key", members, new ResponseListener<Long>() {
  @Override
  public void onSuccess(int count) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
