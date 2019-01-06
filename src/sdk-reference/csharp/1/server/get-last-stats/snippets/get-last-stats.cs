try {
  string last_statistics = kuzzle.server.getLastStats();

  Console.WriteLine(last_statistics);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
