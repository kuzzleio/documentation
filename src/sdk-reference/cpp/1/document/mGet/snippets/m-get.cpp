std::vector<std::string> ids;

ids.push_back("some-id");
ids.push_back("some-other-id");

try {
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-id", R"({"capacity": 4})");
  kuzzle->document->create("nyc-open-data", "yellow-taxi", "some-other-id", R"({"capacity": 7})");

  std::string response = kuzzle->document->mGet(
    "nyc-open-data",
    "yellow-taxi",
    ids);

  std::cout << response << std::endl;
  /*
    [
      {
        "_index": "nyc-open-data",
        "_type": "yellow-taxi",
        "_id": "some-id",
        "_version": 1,
        "found": true,
        "_source": {
          "capacity": 4,
          "_kuzzle_info": {
            "author": "-1",
            "createdAt": 1545411356404,
            "updatedAt": null,
            "updater": null,
            "active": true,
            "deletedAt": null
          }
        }
      },
      {
        "_index": "nyc-open-data",
        "_type": "yellow-taxi",
        "_id": "some-other-id",
        "_version": 1,
        "found": true,
        "_source": {
          "capacity": 7,
          "_kuzzle_info": {
            "author": "-1",
            "createdAt": 1545411356424,
            "updatedAt": null,
            "updater": null,
            "active": true,
            "deletedAt": null
          }
        }
      }
    ]
  */
  std::cout << "Successfully retrieved 2 documents" << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
