try {
  kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");
  kuzzle.auth.deleteMyCredentials("local");

  Console.WriteLine("Credentials Successfully deleted");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
