try {
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-id", R"({"capacity": 4})");
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-other-id", R"({"capacity": 7})");

  std::string body = R"(
    [
      {
        "_id": "some-id",
        "body": {"category": "sedan"}
      },
      {
        "_id": "some-other-id",
        "body": {"category": "limousine"}
      }
    ]
  )";

  std::string response = kuzzle->document->mUpdate("nyc-open-data", "yellow-taxi", body);

  std::cout << response << std::endl;
  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.what() << std::endl;
}
