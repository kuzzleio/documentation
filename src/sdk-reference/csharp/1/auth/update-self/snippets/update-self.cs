try {
  kuzzle.auth.login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  User updatedUser = kuzzle.auth.updateSelf("{\"foo\":\"bar\"}");

  // Prints: {"foo":"bar","profileIds":["default"]}
  Console.WriteLine(updatedUser.content());
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.getMessage());
}
