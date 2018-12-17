try {
  string lastStats = kuzzle.server.getLastStats();
  Console.WriteLine("Last Kuzzle Stats as JSON string: " + lastStats);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
