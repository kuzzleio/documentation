try {
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-id", "{}");
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-other-id", "{}");

  std::string body = R"(
    [
      {
        "_id": "some-id",
        "body": {"capacity": 4}
      },
      {
        "_id": "some-other-id",
        "body": {"capacity": 4}
      }
    ]
  )";

  std::string response = kuzzle->document->mReplace("nyc-open-data", "yellow-taxi", body);

  std::cout << response << std::endl;
  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
