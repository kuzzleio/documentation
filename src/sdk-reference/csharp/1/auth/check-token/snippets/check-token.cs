try {
  string jwt = kuzzle.auth.login(
    "local",
    @"{""username"":""foo"",""password"":""bar""}");

  TokenValidity* res = kuzzle.auth.checkToken(jwt);

  if (res.valid)
    Console.WriteLine("Success");
  else
    Console.Error.WriteLine(res.state);
} catch (KuzzleException e) {
  Console.Error.WriteLine(e.Message());
}
