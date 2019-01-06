try {
  kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");
  bool exists = kuzzle.auth.credentialsExist("local");

  if (exists) {
    Console.WriteLine("Credentials exists for local strategy");
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
