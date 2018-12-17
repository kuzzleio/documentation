try {
  kuzzle.auth.login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  kuzzle.auth.getMyCredentials("local");

  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}
