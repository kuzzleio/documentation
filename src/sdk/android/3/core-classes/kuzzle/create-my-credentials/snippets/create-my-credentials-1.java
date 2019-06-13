
JSONObject credentials = new JSONObject().put("username", "bar");

kuzzle.createMyCredentials("local", credentials, new ResponseListener<JSONObject>() {
  @Override
  public void onSuccess(JSONObject result) {
    // result var contains the new credentials and the kuid of the user
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
}
