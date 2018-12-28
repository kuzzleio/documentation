std::string documents = R"([
  {
    "_id": "some-id",
    "body": { "capacity": 4 }
  },
  {
    "_id": "some-other-id",
    "body": { "capacity": 4 }
  }
])";

try {
  std::string response = kuzzle->document->mCreateOrReplace(
    "nyc-open-data",
    "yellow-taxi",
    documents);

  std::cout << response << std::endl;
  /*
  [
    {
      "_id":"some-id",
      "_source":{
        "_kuzzle_info":{
          "active":true,
          "author":"-1",
          "updater":null,
          "updatedAt":null,
          "deletedAt":null,
          "createdAt":1538552685790
        },
        "capacity":4
      },
      "_index":"nyc-open-data",
      "_type":"yellow-taxi",
      "_version":1,
      "result":"created",
      "_shards":{
        "total":2,
        "successful":1,
        "failed":0
      },
      "created":true,
      "status":201
    },
    {
      "_id":"some-other-id",
      "_source":{
        "_kuzzle_info":{
          "active":true,
          "author":"-1",
          "updater":null,
          "updatedAt":null,
          "deletedAt":null,
          "createdAt":1538552685790
        },
        "capacity":4
      },
      "_index":"nyc-open-data",
      "_type":"yellow-taxi",
      "_version":1,
      "result":"created",
      "_shards":{
        "total":2,
        "successful":1,
        "failed":0
      },
      "created":true,
      "status":201
    }
  ]
  */
  std::cout << "Document successfully created" << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
