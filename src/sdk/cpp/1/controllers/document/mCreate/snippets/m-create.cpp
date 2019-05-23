std::string documents = R"([
  {
    "_id": "some-id",
    "body": { "capacity": 4 }
  },
  {
    "body": { "this": "document id is auto-computed" }
  }
])";

try {
  std::string response = kuzzle->document->mCreate(
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
            "createdAt":1538470871764
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
      "_id":"AWY0AoLgKWETYfLdcMat",
      "_source":{
          "_kuzzle_info":{
            "active":true,
            "author":"-1",
            "updater":null,
            "updatedAt":null,
            "deletedAt":null,
            "createdAt":1538470871764
          },
          "this":"document id is auto-computed"
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

  std::cout << "Documents successfully created" << std::endl;
} catch (kuzzleio::KuzzleException& e) {
  std::cerr << e.what() << std::endl;
}
