try {
  kuzzle.index.create("nyc-open-data");

  Console.WriteLine("Index successfully created");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
