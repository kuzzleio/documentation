kuzzleio::options options = KUZZLE_OPTIONS_DEFAULT;
options.auto_resubscribe = false;

kuzzleio::Kuzzle *kuzzle = new kuzzleio::Kuzzle("kuzzle", &options);
