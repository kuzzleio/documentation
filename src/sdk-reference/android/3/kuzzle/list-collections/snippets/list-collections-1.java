
kuzzle.listCollections("index", new ResponseListener<JSONObject[]>() {
  @Override
  public void onSuccess(JSONObject[] collections) {
    // ...
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
});
