try {
  string mapping = kuzzle.collection.getMapping("nyc-open-data", "yellow-taxi");

  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}
