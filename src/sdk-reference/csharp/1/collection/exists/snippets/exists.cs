try {
  if (kuzzle.collection.exists("nyc-open-data", "green-taxi")) {
    Console.WriteLine("Success");
  }
} catch  {
  Console.Error.WriteLine("");
}
