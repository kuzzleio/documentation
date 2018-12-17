try {
  kuzzle.auth.login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");

  List<std::unique_ptr<UserRight>> rights =
    kuzzle.auth.getMyRights();

  for (int i = 0; i < rights.Count; i++) {
    Console.WriteLine(rights[i].controller + " " + rights[i].action);
    Console.WriteLine(rights[i].index + " " + rights[i].collection);
    Console.WriteLine(rights[i].value);
  }
} catch  {
  Console.Error.WriteLine("");
}
