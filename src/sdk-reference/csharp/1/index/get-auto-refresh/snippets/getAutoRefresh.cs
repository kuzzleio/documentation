if (kuzzle.index.getAutoRefresh("nyc-open-data"))
  Console.WriteLine("autorefresh is true");
else
  Console.Error.WriteLine("autorefresh is false");
