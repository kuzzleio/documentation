try {
  kuzzle.auth.login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  kuzzle.auth.deleteMyCredentials("local");

  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}
