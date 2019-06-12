
kuzzle.getServerInfo(new ResponseListener<JSONObject>() {
  @Override
  public void onSuccess(JSONObject result) {
    // Handle success
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
});
