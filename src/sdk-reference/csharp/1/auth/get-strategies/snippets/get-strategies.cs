try {
  kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");
  List<string> strategies = kuzzle.auth.getStrategies();

  foreach (var strategy in strategies) {
    Console.WriteLine(strategy);
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
