try {
  bool exists = kuzzle.index.exists("nyc-open-data");

  if (exists) {
    Console.WriteLine("Index exists in Kuzzle");
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
