try {
  kuzzle.auth.login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");

  List<UserRight> rights =
    kuzzle.auth.getMyRights();

  for (int i = 0; i < rights.Count; i++) {
    Console.WriteLine(rights[i].controller + " " + rights[i].action);
    Console.WriteLine(rights[i].index + " " + rights[i].collection);
    Console.WriteLine(rights[i].value);
  }
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
