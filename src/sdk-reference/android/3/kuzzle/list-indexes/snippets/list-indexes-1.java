
kuzzle.listIndexes(new ResponseListener<String[]>() {
  @Override
  public void onSuccess(String[] result) {
    // ...
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
});
