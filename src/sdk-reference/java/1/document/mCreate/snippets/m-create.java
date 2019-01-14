String documents = "[" +
  "{" +
    "\"_id\": \"some-id\"," +
    "\"body\": { \"capacity\": 4 }" +
  "}," +
  "{" +
    "\"body\": { \"this\": \"document id is auto-computed\" }" +
  "}" +
"]";

try {
  String response = kuzzle.getDocument().mCreate(
    "nyc-open-data",
    "yellow-taxi",
    documents);

  System.out.println(response);
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

  System.out.println("Documents successfully created");
} catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
