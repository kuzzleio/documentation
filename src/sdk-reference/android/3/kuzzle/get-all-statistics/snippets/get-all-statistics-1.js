
kuzzle.getAllStatistics(new ResponseListener<JSONArray>() {
  @Override
  public void onSuccess(JSONObject[] frames) {
    // loop through all returned frames
  }

  @Override
  public void onError(JSONObject error) {
    // Handle error
  }
};
