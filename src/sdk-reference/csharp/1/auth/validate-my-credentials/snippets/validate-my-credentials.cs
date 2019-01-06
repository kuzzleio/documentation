try {
  kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");
  bool valid = kuzzle.auth.validateMyCredentials("local", @"{""username"":""foo"",""password"":""bar""}");

  if (valid) {
    Console.WriteLine("Credentials are valid");
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
