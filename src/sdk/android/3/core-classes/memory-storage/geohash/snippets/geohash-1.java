
String[] members = {"Palermo", "Catania"};

kuzzle.memoryStorage.geohash("key", members, new ResponseListener<String[]>() {
  @Override
  public void onSuccess(String[] hashes) {
    // callback called once the action has completed
  }

  @Override
  public void onError(JSONObject error) {
  }
});
