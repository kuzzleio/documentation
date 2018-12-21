kuzzle->auth->login("local", R"({"username":"foo","password":"bar"})");

std::string credentials = kuzzle->auth->createMyCredentials(
  "other",
  R"({"username":"foo","password":"bar"})"
);
