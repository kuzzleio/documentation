options options = KUZZLE_OPTIONS_DEFAULT;
options.auto_resubscribe = false;
options.connect = MANUAL;

kuzzleio::Kuzzle *kuzzle = new kuzzleio::Kuzzle("kuzzle", &options);
