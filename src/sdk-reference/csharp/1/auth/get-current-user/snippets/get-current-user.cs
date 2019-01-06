try {
  kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");
  User user = kuzzle.auth.getCurrentUser();

  Console.WriteLine("Successfully got current user");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
