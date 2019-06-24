
kuzzle.security.hasCredentials("local", "kuid", new ResponseListener<Boolean>() {
  @Override
  public void onSuccess(Boolean result) {

  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
}
