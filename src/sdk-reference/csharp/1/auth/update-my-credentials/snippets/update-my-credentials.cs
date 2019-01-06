try {
  kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");
  kuzzle.auth.updateMyCredentials(
    "local",
    @"{""username"":""foo"",""password"":""bar"",""other"":""value""}");

  Console.WriteLine("Credentials successfully updated");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
