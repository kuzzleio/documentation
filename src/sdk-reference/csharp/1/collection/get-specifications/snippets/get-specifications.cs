try {
  string specifications = kuzzle.collection.getSpecifications("nyc-open-data", "yellow-taxi");

  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}
