try {
  kuzzle.index.refresh("nyc-open-data");

  Console.WriteLine("0 shards fail to refresh");
} catch  {
  Console.Error.WriteLine("");
}
