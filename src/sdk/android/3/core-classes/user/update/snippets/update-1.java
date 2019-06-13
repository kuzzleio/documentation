
JSONObject updateContent = new JSONObject()
  .put("firstname", "My Name Is")
  .put("lastname", "Jonas");

user.update(updateContent, new ResponseListener<User>() {
  @Override
  public void onSuccess(User updatedUser) {

  }

  @Override
  public void onError(JSONObject error) {

  }
});
