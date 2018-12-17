try {
  string config = kuzzle.server.getConfig();

  Console.WriteLine("Kuzzle Server configuration as JSON string: " + config);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
