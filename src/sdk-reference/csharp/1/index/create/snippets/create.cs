try {
  kuzzle.index.create("nyc-open-data");
  Console.WriteLine("index created");
} catch  {
  Console.Error.WriteLine("");
}
