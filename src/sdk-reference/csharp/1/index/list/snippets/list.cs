try {
List<string> indexes = kuzzle.index.list();

Console.WriteLine("Kuzzle contains " + indexes.Count + " indexes");
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
