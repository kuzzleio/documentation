kuzzleio::Kuzzle *kuzzle = new kuzzleio::Kuzzle(
  new kuzzleio::WebSocket("kuzzle"));

kuzzle->autoReplay(true);

kuzzle->volatiles(R"({ username: "Aschen" })");
