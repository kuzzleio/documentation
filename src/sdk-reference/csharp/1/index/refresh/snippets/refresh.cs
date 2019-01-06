try {
  kuzzle.index.refresh("nyc-open-data");

  Console.WriteLine("Index successfully refreshed");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
