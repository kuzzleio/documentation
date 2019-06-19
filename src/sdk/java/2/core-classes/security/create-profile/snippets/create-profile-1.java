
JSONObject[] policies = new JSONObject[]{
  new JSONObject().put("roleId", "myrole"),
  new JSONObject()
    .put("roleId", "default")
    .put("restrictedTo", new JSONArray()
      .put(new JSONObject().put("index", "index1"))
      .put(new JSONObject()
        .put("index", "index2")
        .put("collections",new JSONArray().put("foo").put("bar"))
      )
    )
};

Options opts = new Options().setReplaceIfExist(true);

kuzzle
  .security
  .createProfile("myprofile", policies, opts, new ResponseListener<Profile>() {
    @Override
    public void onSuccess(Profile profile) {

    }

    @Override
    public void onError(JSONObject error) {

    }
  });
