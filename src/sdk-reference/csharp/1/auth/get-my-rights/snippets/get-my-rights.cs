try {
  kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");

  List<std::shared_ptr<UserRight>> rights =
    kuzzle.auth.getMyRights();

  for (auto right : rights) {
    Console.WriteLine(right.controller() + " " + right.action());
    Console.WriteLine(right.index() + " " + right.collection());
    Console.WriteLine(right.value());
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
