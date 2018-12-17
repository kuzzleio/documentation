try {
  kuzzle.auth.login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  User user = kuzzle.auth.getCurrentUser();

  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}
