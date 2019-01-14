try {
  kuzzle.getDocument().create(
    "nyc-open-data",
    "yellow-taxi",
    "some-id",
    "{\"color\": \"yellow\"}");

  String response = kuzzle.getDocument().replace(
    "nyc-open-data",
    "yellow-taxi",
    "some-id",
    "{" +
      "\"capacity\": 4," +
      "\"category\": \"sedan\"" +
    "}");

  System.out.println(response);
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
    }
  }
  */
  System.out.println("Document successfully replaced");
} catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
