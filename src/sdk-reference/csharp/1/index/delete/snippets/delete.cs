try {
  kuzzle.index.delete("nyc-open-data");
  Console.WriteLine("index deleted");
} catch  {
  Console.Error.WriteLine("");
}
