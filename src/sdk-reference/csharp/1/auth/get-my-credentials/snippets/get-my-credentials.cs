try {
  kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");
  string local_credentials = kuzzle.auth.getMyCredentials("local");

  Console.WriteLine(local_credentials);
  Console.WriteLine("Successfully got local credentials");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
