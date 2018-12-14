try {
  string specifications = kuzzle.collection.getSpecifications("nyc-open-data", "yellow-taxi");

  Console.WriteLine("Success");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
