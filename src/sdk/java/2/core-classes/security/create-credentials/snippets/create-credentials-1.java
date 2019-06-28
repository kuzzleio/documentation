
JSONObject credentials = new JSONObject().put("username", "bar");

kuzzle.security.createCredentials("local", "kuid", credentials, new ResponseListener<JSONObject>() {
  @Override
  public void onSuccess(JSONObject credentials) {

  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
}
