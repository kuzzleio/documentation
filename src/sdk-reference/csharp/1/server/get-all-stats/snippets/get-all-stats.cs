try {
  string statistics = kuzzle.server.getAllStats();

  Console.WriteLine(statistics);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
