
JSONObject content = new JSONObject();

JSONObject newUser = new JSONObject().put("content", content);

JSONObject credentials = new JSONObject()
  .put("local", new JSONObject()
  // The "local" authentication strategy requires a password
  .put("password", "secret password")
  .put("lastLoggedIn", 1494411803));

newUser.put("credentials", credentials);

Options opts = new Options().setReplaceIfExist(true);

kuzzle
  .security
  .createUser("myNewUser", newUser, opts, new ResponseListener<User>() {
    @Override
    public void onSuccess(User user) {

    }

    @Override
    public void onError(JSONObject error) {

    }
  });
