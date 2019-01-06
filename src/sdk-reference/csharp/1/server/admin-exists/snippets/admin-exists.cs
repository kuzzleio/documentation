try {
  bool exists = kuzzle.server.adminExists();

  if (exists) {
    Console.WriteLine("Admin user exists");
  } else {
    Console.WriteLine("Admin user does not exists");
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
