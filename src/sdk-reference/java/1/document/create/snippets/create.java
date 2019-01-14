try {
  String response;
  response = kuzzle.getDocument().create(
    "nyc-open-data",
    "yellow-taxi",
    "some-id",
    "{\"lastName\": \"Eggins\"}");

  System.out.println(response);
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
      "lastName": "Eggins",
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

  System.out.println("Document successfully created");
} catch (KuzzleException e) {
  System.err.println(e.getMessage());
}
