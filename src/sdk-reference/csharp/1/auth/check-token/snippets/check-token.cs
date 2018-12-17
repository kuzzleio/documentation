try {
  string jwt = kuzzle.auth.login("local", "{\"username\":\"foo\",\"password\":\"bar\"}");
  token_validity* res = kuzzle.auth.checkToken(jwt);
  if (res.valid)
    Console.WriteLine("Success");
  else
    Console.Error.WriteLine(res.state);
} catch  {
  Console.Error.WriteLine("");
}
