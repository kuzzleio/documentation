try {
  kuzzle->document->create(
    "nyc-open-data",
    "yellow-taxi",
    "some-id",
    R"({"capacity": 4})");

  std::string response =
    kuzzle->document->get("nyc-open-data", "yellow-taxi", "some-id");
  /*
  {
    "_index":"nyc-open-data",
    "_type":"yellow-taxi",
    "_id":"some-id",
    "_version":1,
    "found":true,
    "_source":{
        "capacity":4,
        "_kuzzle_info":{
          "author":"-1",
          "createdAt":1538402859880,
          "updatedAt":null,
          "updater":null,
          "active":true,
          "deletedAt":null
        }
    }
  }
  */

  std::cout << "Success" << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
