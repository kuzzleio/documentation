
JSONObject[] entries = new JSONObject[]{
  new JSONObject().put("field", "field1").put("value", "foo"),
  new JSONObject().put("field", "field2").put("value", "bar"),
  new JSONObject().put("field", "...").put("value", "...")
};

kuzzle.memoryStorage.hmset("key", entries, new ResponseListener<Void>() {
  @Override
  public void onSuccess(Void v) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
