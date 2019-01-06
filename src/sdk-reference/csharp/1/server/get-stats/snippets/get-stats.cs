try {
  time_t start = std::time(0);
  time_t stop = std::time(0);

  string stats = kuzzle.server.getStats(start, stop);

  Console.WriteLine("Kuzzle Stats as JSON string: " + stats);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
