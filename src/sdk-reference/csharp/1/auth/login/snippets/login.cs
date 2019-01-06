try {
  string jwt = kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");

  Console.WriteLine("Success");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
