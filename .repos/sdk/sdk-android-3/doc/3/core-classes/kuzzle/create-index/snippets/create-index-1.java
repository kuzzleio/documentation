
kuzzle.createIndex("myIndex", new ResponseListener<Boolean>() {
  @Override
  public void onSuccess(JSONObject result) {
    // result var contains the creation status of myIndex.
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
}
