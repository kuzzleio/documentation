try {
  kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");

  List<UserRight> rights =
    kuzzle.auth.getMyRights();

  foreach (var right in rights) {
    Console.WriteLine(right.controller() + " " + right.action());
    Console.WriteLine(right.index() + " " + right.collection());
    Console.WriteLine(right.value());
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
