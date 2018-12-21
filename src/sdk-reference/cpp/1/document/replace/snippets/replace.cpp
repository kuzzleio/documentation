try {
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-id", R"({"color": "yellow"})");

  std::string response = kuzzle->document->replace("nyc-open-data", "yellow-taxi", "some-id", R"({
    "capacity": 4,
    "category": "sedan"
  })");

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
    },
    "created": false,
    "_source": {
      "capacity": 4,
      "category": "sedan",
      "_kuzzle_info": {
        "author": "-1",
        "createdAt": 1538641029988,
        "updatedAt": 1538641029988,
        "updater": "-1",
        "active": true,
        "deletedAt": null
      }
    },
    "_meta": {
      "author": "-1",
      "createdAt": 1538641029988,
      "updatedAt": 1538641029988,
      "updater": "-1",
      "active": true,
      "deletedAt": null
    }
  }
  */
  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException e) {
  std::cerr << e.what() << std::endl;
}
