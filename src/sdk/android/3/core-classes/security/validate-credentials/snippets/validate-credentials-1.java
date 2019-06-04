
JSONObject credentials = new JSONObject().put("username", "bar");

kuzzle.security.validateCredentials("local", "kuid", credentials, new ResponseListener<Boolean>() {
  @Override
  public void onSuccess(Boolean result) {

  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
}
