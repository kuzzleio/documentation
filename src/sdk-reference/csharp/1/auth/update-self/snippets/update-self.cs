try {
  kuzzle.auth.login("local", @"{""username"":""foo"",""password"":""bar""}");
  User updatedUser = kuzzle.auth.updateSelf(@"{""age"": 42}");

  Console.WriteLine(updatedUser.content());
  // {"age": 42}
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.what());
}
