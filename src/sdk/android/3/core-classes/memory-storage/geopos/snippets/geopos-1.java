
String[] members = {"Palermo", "Catania"};

kuzzle.memoryStorage.geopos("key", members, new ResponseListener<Double[][]>() {
  @Override
  public void onSuccess(Double[][] positions) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
