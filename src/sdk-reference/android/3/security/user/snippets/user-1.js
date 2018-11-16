
JSONObject userContent = new JSONObject()
    // A "profileIds" field is required to bind a user to an existing profile
    .put("profileIds", new JSONArray().put('someProfile'))
    // The "local" authentication strategy requires a password
    .put("password", "a password")
    // You can also set custom fields to your user
    .put("firstname", "John")
    .put("lastname", "Doe");

User user = kuzzle.security.user("<kuid>", userContent);  
