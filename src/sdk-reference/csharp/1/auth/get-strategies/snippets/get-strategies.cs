try {
  kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");
  List<string> strategies = kuzzle.auth.getStrategies();

  for (auto strategy : strategies) {
    Console.WriteLine(strategy);
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
