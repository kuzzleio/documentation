try {
  bool autorefresh_flag = kuzzle.index.getAutoRefresh("nyc-open-data");

  if (autorefresh_flag) {
    Console.WriteLine("Autorefresh is enabled");
  } else {
    Console.Error.WriteLine("Autorefresh is disabled");
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
