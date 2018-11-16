
kuzzle.security.getCredentials("local", "kuid", new ResponseListener<JSONObject>() {
  @Override
  public void onSuccess(JSONObject credentials) {

  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
}
