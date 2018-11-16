String doc = "{\"capacity\": 4}";

try {
    kuzzle.getDocument().create("nyc-open-data", "yellow-taxi", "some-id", doc);

    String response = kuzzle.getDocument().get("nyc-open-data", "yellow-taxi", "some-id");
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

    System.out.println("Success");
} catch (KuzzleException e) {
    System.err.println(e.getMessage());
}
