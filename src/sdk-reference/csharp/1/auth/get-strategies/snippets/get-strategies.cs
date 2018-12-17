try {
  kuzzle.auth.login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  kuzzle.auth.getStrategies();

  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}