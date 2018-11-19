
JSONObject[] entries = new JSONObject[]{
  new JSONObject().put("key", "key1").put("value", "foo"),
  new JSONObject().put("key", "key2").put("value", "bar"),
  new JSONObject().put("key", "...").put("value", "...")
};

kuzzle.memoryStorage.mset(entries, new ResponseListener<Void>() {
  @Override
  public void onSuccess(Void v) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
