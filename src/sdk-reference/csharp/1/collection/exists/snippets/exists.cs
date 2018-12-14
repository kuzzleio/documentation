try {
  if (kuzzle.collection.exists("nyc-open-data", "green-taxi")) {
    Console.WriteLine("Success");
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
