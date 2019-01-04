try {
  kuzzle.collection.truncate("nyc-open-data", "yellow-taxi");

  Console.WriteLine("Collection successfully truncated");
} catch  {
  Console.Error.WriteLine(e.what());
}
