try {
  kuzzle.index.delete("nyc-open-data");

  Console.WriteLine("Index successfully deleted");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
