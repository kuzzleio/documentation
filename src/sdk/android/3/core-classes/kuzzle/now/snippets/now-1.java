
kuzzle.now(new ResponseListener<Date>() {
  @Override
  public void onSuccess(Date object) {
    // 'object' contains the Kuzzle timestamp (utc, in milliseconds)
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
});
