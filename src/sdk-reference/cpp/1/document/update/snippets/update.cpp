try {
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-id", R"({"capacity": 4})");
  std::string response = kuzzle->document->update("nyc-open-data", "yellow-taxi", "some-id", R"({"category": "suv"})");

  std::cout << response << std::endl;
  /*
  {
    "_index": "nyc-open-data",
    "_type": "yellow-taxi",
    "_id": "some-id",
    "_version": 2,
    "result": "updated",
    "_shards": {
      "total": 2,
      "successful": 1,
      "failed": 0
    }
  }
  */
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.getMessage() << std::endl;
}
