kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");

string credentials = kuzzle.auth.createMyCredentials(
  "other",
  @"{""username"":""foo"",""password"":""bar""}");
