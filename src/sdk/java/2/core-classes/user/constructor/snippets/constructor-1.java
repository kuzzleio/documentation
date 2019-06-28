
JSONObject userContent = new JSONObject()
  // A "profile" field is required to bind a user to an existing profile
  .put("profileIds", new JSONArray().put("admin"))
  // The "local" authentication strategy requires a password
  .put("password", "secret password")
  // You can also set custom fields to your user
  .put("firstname", "John")
  .put("lastname", "Doe");

// Using the KuzzleSecurity factory:
User user = kuzzle.security.user("user ID", userContent);

// Or directly with the constructor:
User user = new User(kuzzle.security, "user ID", userContent);
