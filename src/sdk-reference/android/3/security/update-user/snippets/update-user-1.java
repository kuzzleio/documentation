
JSONObject newContent = new JSONObject()
  .put("firstname", "My Name Is")
  .put("lastname", "Jonas");

kuzzle
  .security
  .updateUser("User ID", newContent, new ResponseListener<User>() {
    @Override
    public void onSuccess(User user) {

    }

    @Override
    public void onError(JSONObject error) {

    }
  });
