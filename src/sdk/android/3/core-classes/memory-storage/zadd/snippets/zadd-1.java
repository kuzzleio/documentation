
JSONObject[] elements = new JSONObject[]{
  new JSONObject().put("score", 1).put("member", "foo"),
  new JSONObject().put("score", 2).put("member", "bar"),
  new JSONObject().put("score", 3).put("member", "baz")
};

kuzzle.memoryStorage.zadd("key", elements new ResponseListener<Long>() {
  public void onSuccess(int count) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
