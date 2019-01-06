try {
  kuzzle.connect();

  Console.WriteLine("Successfully connected");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
