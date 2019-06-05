
JSONObject policy1 = new JSONObject().put("roleId", "myrole");

JSONObject updateContent = new JSONObject()
  .put("policies", new JSONArray().put(policy1));

profile.update(updateContent, new ResponseListener<Profile>() {
  @Override
  public void onSuccess(Profile updatedProfile) {

  }

  @Override
  public void onError(JSONObject error) {

  }
});
