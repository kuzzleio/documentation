
kuzzle.memoryStorage.geodist("key", "Palermo", "Catania", new ResponseListener<double>() {
  @Override
  public void onSuccess(double distance) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
