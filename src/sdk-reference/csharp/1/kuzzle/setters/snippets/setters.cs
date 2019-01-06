Kuzzle *kuzzle = new Kuzzle(
  new WebSocket("kuzzle"));

kuzzle.setAutoReplay(true);

kuzzle.setVolatile(@"{ username: ""Aschen"" }");
