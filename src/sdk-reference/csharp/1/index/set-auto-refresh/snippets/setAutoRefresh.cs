try {
  kuzzle.index.setAutoRefresh("nyc-open-data", true);

  Console.WriteLine("Autorefresh is now enabled on index");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
