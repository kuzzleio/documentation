try {
  string kuzzle_info = kuzzle.server.info();

  Console.WriteLine(kuzzle_info);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
