try {
  kuzzle.collection.truncate("nyc-open-data", "yellow-taxi");

  Console.WriteLine("Collection successfully truncated");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
