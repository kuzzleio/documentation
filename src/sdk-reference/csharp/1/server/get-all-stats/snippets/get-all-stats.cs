try {
  string allStats = kuzzle.server.getAllStats();
  Console.WriteLine("All Kuzzle Stats as JSON string: " + allStats);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
