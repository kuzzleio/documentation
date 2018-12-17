try {
  kuzzle.auth.login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  kuzzle.auth.validateMyCredentials("local", "{\"username\":\"foo\",\"password\":\"bar\"}");

  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}
