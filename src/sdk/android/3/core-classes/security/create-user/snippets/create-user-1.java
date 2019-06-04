
JSONObject content = new JSONObject()
  // A "profileIds" field is required to bind a user to existing profiles
  .put("profileIds", new JSONArray()
    .put("admin")
  )
  // You can also set custom fields to your user
  .put("firstname", "John")
  .put("lastname", "Doe");

JSONObject newUser = new JSONObject().put("content", content);

JSONObject credentials = new JSONObject()
  // Authentication strategy to use
  .put("local", new JSONObject()
    // The necessary information to provide vary,
    // depending on the chosen authentication strategy
    .put("password", "secret password")
    .put("username", "jdoe")
  );

newUser.put("credentials", credentials);

Options opts = new Options();

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
