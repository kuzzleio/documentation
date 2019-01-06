try {
  bool exists = kuzzle.collection.exists("nyc-open-data", "green-taxi");

  if (exists) {
    Console.WriteLine("Collection green-taxi exists");
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
