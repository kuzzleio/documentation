
JSONObject credentials = new JSONObject().put("username", "foo");

kuzzle.security.updateCredentials("local", "kuid", credentials, new ResponseListener<JSONObject>() {
  @Override
  public void onSuccess(JSONObject updatedCredentials) {
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
}
