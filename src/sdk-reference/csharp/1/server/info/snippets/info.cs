try {
  string info = kuzzle.server.info();
  Console.WriteLine("Kuzzle Server information as JSON string: " + info);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
