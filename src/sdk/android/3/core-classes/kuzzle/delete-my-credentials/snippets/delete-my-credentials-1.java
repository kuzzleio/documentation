
kuzzle.deleteMyCredentials("local", new ResponseListener<JSONObject>() {
  @Override
  public void onSuccess(JSONObject result) {
    // result var contains the query status
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
}
