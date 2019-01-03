kuzzleio::Kuzzle *kuzzle = new kuzzleio::Kuzzle(
  new kuzzleio::WebSocket("kuzzle"));

kuzzle->setAutoReplay(true);

kuzzle->setVolatile(R"({ username: "Aschen" })");
