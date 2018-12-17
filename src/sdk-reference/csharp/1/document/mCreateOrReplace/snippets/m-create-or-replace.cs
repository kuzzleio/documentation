string body = @"[
  {
    ""_id"": ""some-id"",
    ""body"": { ""capacity"": 4 }
  },
  {
    ""_id"": ""some-other-id"",
    ""body"": { ""capacity"": 4 }
  }
]";

try {
  string response = kuzzle.document.mCreateOrReplace("nyc-open-data", "yellow-taxi", body);

  Console.WriteLine(response);
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
      "status":201,
      "_meta":{
        "active":true,
        "author":"-1",
        "updater":null,
        "updatedAt":null,
        "deletedAt":null,
        "createdAt":1538552685790
      }
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
      "status":201,
      "_meta":{
        "active":true,
        "author":"-1",
        "updater":null,
        "updatedAt":null,
        "deletedAt":null,
        "createdAt":1538552685790
      }
    }
  ]
  */
  Console.WriteLine("Success");
} catch  {
  Console.Error.WriteLine("");
}