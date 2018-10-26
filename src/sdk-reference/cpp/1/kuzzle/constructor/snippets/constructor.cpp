kuzzleio::options options;
options.auto_resubscribe = false;

kuzzleio::Kuzzle *kuzzle = new kuzzleio::Kuzzle("kuzzle", &options);
