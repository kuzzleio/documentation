try {
  kuzzle.collection.truncate("nyc-open-data", "yellow-taxi");

  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}
