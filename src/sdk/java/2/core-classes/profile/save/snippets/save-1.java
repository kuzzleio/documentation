
JSONObject policy1 = new JSONObject()
  .put("roleId", "myrole");

JSONObject policy2 = new JSONObject()
  .put("roleId", "default")
  .put("restrictedTo", new JSONArray()
    .put(new JSONObject().put("index", "index1"))
    .put(new JSONObject()
      .put("index", "index2")
      .put("collections",new JSONArray().put("foo").put("bar"))
    )
  );

JSONObject roles = new JSONObject()
  .put("policies", new JSONArray()
    .put(policy1)
    .put(policy2)
  );

Profile profile = kuzzle.security.profile("myprofile", roles);

profile.save(new ResponseListener<Profile>() {
  @Override
  public void onSuccess(Profile savedProfile) {

  }

  @Overrid public void onError(JSONObject error) {

  }
});
