
JSONObject policy = new JSONObject()
  .put("roleId", "some role id")
  .put("restrictedTo", new JSONArray()
    .put(new JSONObject().put("index", "index1"))
    .put(new JSONObject()
      .put("index", "index2")
      .put("collections", new JSONArray().put("foo").put("bar"))
    )
  );

profile.addPolicy(policy);

// you may also add a role ID directly
profile.addPolicy("some role id");
