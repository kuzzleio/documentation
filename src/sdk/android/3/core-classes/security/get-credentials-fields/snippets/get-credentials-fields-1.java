
kuzzle.security.getCredentialFields("local", new ResponseListener<String[]>() {
  @Override
  public void onSuccess(String[] fields) {

  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
}
