try {
  bool exists = kuzzle.server.adminExists();

  Console.WriteLine("Admin exists? " + exists);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
