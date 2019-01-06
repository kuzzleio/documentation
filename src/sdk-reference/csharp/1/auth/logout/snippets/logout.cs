try {
  kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");
  kuzzle.auth.logout();

  Console.WriteLine("Success");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
