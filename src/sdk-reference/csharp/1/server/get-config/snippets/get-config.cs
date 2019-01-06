try {
  string configuration = kuzzle.server.getConfig();

  Console.WriteLine(configuration);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
