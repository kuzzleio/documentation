try {
  kuzzle.collection.deleteSpecifications("nyc-open-data", "yellow-taxi");

  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}
