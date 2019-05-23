String documents = "["
    + "   {"
    + "       \"_id\": \"some-id\","
    + "       \"body\": { \"capacity\": 4 }"
    + "   },"
    + "   {"
    + "       \"_id\": \"some-other-id\","
    + "       \"body\": { \"capacity\": 4 }"
    + "   }"
    + "]";

try {
    String response = kuzzle.getDocument().mCreateOrReplace(
      "nyc-open-data",
      "yellow-taxi",
      documents
    );

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
    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}