try {
  kuzzle.auth.login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  kuzzle.auth.updateMyCredentials("local", "{\"username\":\"foo\",\"password\":\"bar\",\"other\":\"value\"}");

  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}
