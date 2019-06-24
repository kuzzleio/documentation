
kuzzle.security.deleteCredentials("local", "kuid", new ResponseListener<JSONObject>() {
  @Override
  public void onSuccess(JSONObject result) {

  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
}
