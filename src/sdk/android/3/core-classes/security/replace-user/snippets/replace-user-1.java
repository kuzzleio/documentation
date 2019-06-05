
JSONObject newContent = new JSONObject()
  .put("profileIds", new JSONArray()
    .put("admin")
  )
  .put("firstname", "My Name Is")
  .put("lastname", "Jonas");

kuzzle
  .security
  .replaceUser("User ID", newContent, new ResponseListener<User>() {
    @Override
    public void onSuccess(User user) {

    }

    @Override
    public void onError(JSONObject error) {

    }
  });
