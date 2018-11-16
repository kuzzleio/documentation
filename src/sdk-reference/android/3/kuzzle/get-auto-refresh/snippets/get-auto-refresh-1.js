
kuzzle.getAutoRefresh("myIndex", new ResponseListener<Boolean>() {
  @Override
  public void onSuccess(Boolean autoRefresh) {
    // autoRefresh var contains the autoRefresh status of myIndex.
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
}
