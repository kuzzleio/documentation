try {
  kuzzle.index.refreshInternal();

  Console.WriteLine("Internal index successfully refreshed");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
