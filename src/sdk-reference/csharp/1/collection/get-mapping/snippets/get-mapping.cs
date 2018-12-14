try {
  string mapping = kuzzle.collection.getMapping("nyc-open-data", "yellow-taxi");

  Console.WriteLine("Success");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
