
JSONObject[] points = new JSONObject[]{
  new JSONObject()
    .put("lon", 13.361389)
    .put("lat", 38.115556)
    .put("name", "Palermo"),
  new JSONObject()
    .put("lon", 15.087269)
    .put("lat", 37.502669)
    .put("name", "Catania")
};

kuzzle.memoryStorage.geoadd("key", points, new ResponseListener<Long>() {
  @Override
  public void onSuccess(int count) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
