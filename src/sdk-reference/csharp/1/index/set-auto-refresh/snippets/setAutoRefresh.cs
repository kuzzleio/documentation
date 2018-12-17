try {
  kuzzle.index.setAutoRefresh("nyc-open-data", true);

  Console.WriteLine("autorefresh flag is set to true");
} catch  {
  Console.Error.WriteLine("");
}
