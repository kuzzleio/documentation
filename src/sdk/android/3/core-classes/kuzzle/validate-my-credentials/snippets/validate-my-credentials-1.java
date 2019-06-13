
JSONObject credentials = new JSONObject().put("username", "bar");

kuzzle.validateMyCredentials("local", credentials, new ResponseListener<Boolean>() {
  @Override
  public void onSuccess(Boolean result) {
    // result var contains either true or false
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
}
