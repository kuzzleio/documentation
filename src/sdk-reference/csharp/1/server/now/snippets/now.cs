try {
  int64_t timestamp = kuzzle.server.now();
  Console.WriteLine("Epoch-millis timestamp: " + timestamp);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
