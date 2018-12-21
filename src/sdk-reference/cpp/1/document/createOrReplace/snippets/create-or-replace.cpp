try {
  std::string response = kuzzle->document->createOrReplace(
    "nyc-open-data",
    "yellow-taxi",
    "some-id",
    R"({"lastName": "McHan"})");

  std::cout << response << std::endl;
  /*
  {
    "_index": "nyc-open-data",
    "_type": "yellow-taxi",
    "_id": "some-id",
    "_version": 1,
    "result": "created",
    "_shards": {
      "total": 2,
      "successful": 1,
      "failed": 0
    },
    "created": true,
    "_source": {
      "lastName": "McHan",
      "_kuzzle_info": {
        "author": "-1",
        "createdAt": 1537445737667,
        "updatedAt": null,
        "updater": null,
        "active": true,
        "deletedAt": null
      }
    }
  }
  */

  std::cout << "Document successfully created" << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
