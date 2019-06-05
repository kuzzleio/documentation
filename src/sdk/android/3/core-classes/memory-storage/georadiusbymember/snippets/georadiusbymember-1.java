
kuzzle.memoryStorage.georadiusbymember("key", "Palermo", 200, "km", new ResponseListener<JSONObject[]>() {
  @Override
  public void onSuccess(JSONObject[] points) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
